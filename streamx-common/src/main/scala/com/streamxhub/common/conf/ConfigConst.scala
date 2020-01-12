package com.streamxhub.common.conf

object ConfigConst {

  /**
   *
   * about parameter...
   */

  val KEY_APP_HOME = "app.home"

  val KEY_HOST = "host"

  val KEY_PORT = "port"

  val KEY_DB = "db"

  val KEY_USER = "user"

  val KEY_PASSWORD = "password"


  /**
   * sign....
   */
  val SIGN_COLON = ":"

  val SIGN_SEMICOLON = ";"

  val SIGN_COMMA = ","

  val SIGN_EMPTY = ""

  //flink.....

  val KEY_FLINK_APP_CONF = "flink.conf"

  val KEY_FLINK_CHECKPOINT_INTERVAL = "flink.checkpoint.interval"

  val KEY_FLINK_PARALLELISM = "flink.parallelism"

  val KEY_FLINK_RESTART_ATTEMPTS = "flink.restart.attempts"

  val KEY_FLINK_DELAY_ATTEMPTS = "flink.delay.attempts"

  val KEY_FLINK_CHECKPOINT_MODE = "flink.checkpoint.mode"

  val KEY_FLINK_TIME_CHARACTERISTIC = "flink.time.characteristic"

  val KEY_FLINK_APP_NAME = "flink.deployment.resource.yarnname"

  /**
   * about sink prefix
   */

  val TOPIC = "topic"

  val SINK_KAFKA_PREFIX = "sink.kafka."

  val SINK_REDIS_PREFIX = "sink.redis."

  val SINK_MYSQL_PREFIX = "sink.mysql"

  /**
   * about config MySQL
   */
  val KEY_MYSQL_INSTANCE = "instance"
  val KEY_MYSQL_DRIVER = "driverClassName"
  val KEY_MYSQL_URL = "jdbcUrl"
  val KEY_MYSQL_USER = "username"
  val KEY_MYSQL_PASSWORD = "password"


  val SINK_ES_PREFIX = "sink.es."

  val KEY_ES_AUTH_USER = "sink.es.auth.user"

  val KEY_ES_AUTH_PASSWORD = "sink.es.auth.password"

  val KEY_ES_REST_MAX_RETRY = "sink.es.rest.max.retry.timeout"

  val KEY_ES_REST_CONTENT_TYPE = "sink.es.rest.content.type"

  val KEY_ES_CONN_REQ_TIME_OUT = "sink.es.connect.request.timeout"

  val KEY_ES_CONN_TIME_OUT = "sink.es.connect.timeout"

  val KEY_ES_CLUSTER_NAME = "sink.es.cluster.name"

  val SINK_ES_CONF_BULK_PREFIX = "bulk.flush."

  val KEY_ES_CLIENT_TRANSPORT_SNIFF = "client.transport.sniff"

  /**
   * about source prefix
   */
  val SOURCE_KAFKA_PREFIX = "source.kafka."

  val SOURCE_MYSQL_PREFIX = "source.mysql"

}
