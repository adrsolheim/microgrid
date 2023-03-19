FROM confluentinc/cp-kafka-connect-base:7.3.2

RUN confluent-hub install --no-prompt debezium/debezium-connector-mysql:2.0.1  \
 && confluent-hub install --no-prompt confluentinc/kafka-connect-jdbc:10.6.4
