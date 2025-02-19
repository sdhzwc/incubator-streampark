/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.streampark.flink.cli

import org.apache.streampark.common.conf.ConfigConst.{KEY_APP_CONF, KEY_FLINK_SQL, KEY_FLINK_TABLE_MODE}
import org.apache.streampark.common.util.{DeflaterUtils, PropertiesUtils}
import org.apache.streampark.flink.core.{SqlCommand, SqlCommandParser}
import org.apache.streampark.flink.core.scala.{FlinkStreamTable, FlinkTable}

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.configuration.ExecutionOptions

import scala.language.implicitConversions
import scala.util.{Failure, Success, Try}

object SqlClient extends App {

  private[this] val parameterTool = ParameterTool.fromArgs(args)

  private[this] val flinkSql = {
    val sql = parameterTool.get(KEY_FLINK_SQL())
    require(sql != null && sql.trim.nonEmpty, "Usage: flink sql cannot be null")
    Try(DeflaterUtils.unzipString(sql)) match {
      case Success(value) => value
      case Failure(_) =>
        throw new IllegalArgumentException("Usage: flink sql is invalid or null, please check")
    }
  }

  private[this] val sets = SqlCommandParser.parseSQL(flinkSql).filter(_.command == SqlCommand.SET)

  private[this] val defaultMode = "streaming"

  private[this] val mode = sets.find(_.operands.head == ExecutionOptions.RUNTIME_MODE.key()) match {
    case Some(e) =>
      // 1) flink sql execution.runtime-mode has highest priority
      e.operands(1)
    case None =>
      // 2) dynamic properties execution.runtime-mode
      parameterTool.get(ExecutionOptions.RUNTIME_MODE.key(), null) match {
        case null =>
          parameterTool.get(KEY_APP_CONF(), null) match {
            case null => defaultMode
            case f =>
              val parameter = PropertiesUtils.fromYamlText(DeflaterUtils.unzipString(f.drop(7)))
              // 3) application conf execution.runtime-mode
              parameter.getOrElse(KEY_FLINK_TABLE_MODE, defaultMode)
          }
        case m => m
      }
  }

  mode match {
    case "batch" => BatchSqlApp.main(args)
    case "streaming" => StreamSqlApp.main(args)
    case _ =>
      throw new IllegalArgumentException(
        "Usage: runtime execution-mode invalid, optional [streaming|batch]")
  }

  private[this] object BatchSqlApp extends FlinkTable {
    override def handle(): Unit = context.sql()
  }

  private[this] object StreamSqlApp extends FlinkStreamTable {
    override def handle(): Unit = context.sql()
  }

}
