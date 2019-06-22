# Kafka-Demo

## Conceitos:

### Streaming:
É o ato de processar novos dados no momento em que eles são gerados, deste modo não é obrigatório os mesmos ja estarem armazenados em sua plataforma de banco de dados.

### Cluster:
Grupo de computadores atuando em conjunto para um mesmo propósito.

### Broker:
Padrão de projeto arquitetural utilizado para validação, transformação e routeamento de mensagem. Ele é responsável por traduzir a mensagem de sua forma formal do protocólo que foi enviada para a formal do protocólo que foi recebida.

### Producer:
Producer é o responsável por enviar mensagens para o kafka.

### Consumer:
Consumer é uma aplicação que recebe uma partição do kafka.

### Stream Processors:
Eles recebem tópicos e refinam as mensagens, os publicando de volta no broker dando uma melhor tratativa nos dados.

### Consumer group:
Vários consumers que irão atuar como se representasse apenas uma unidade lógica com o mesmo objetivo, onde cada consumer irá consumir uma partição do tópico produzido.

### KafkaCluster:
Responsável por agrupar vários containers que estão rodando uma instância do KafkaBroker.

### KafkaBroker:
Responsável por armazenar e entregar as mensagens para os consumers interessados nelas.

### Zookeper:
Reponsável por eleger lideres para cada grupo de consumers consumirem as partições.

### Mensagens:
Array de bytes, podendo ser uma string, Json, etc....

### Topicos:
Identificador para o KafkaBroker saber quais mensagens mandar para cada consumer, ao ser gerado um evento o producer define qual evento é gerado a partir deste tópico, assim ele é armazenado dentro do KafkaBroker que disponibiliza para os consumers interessados nestes tópicos.

### Partition:
As vezes uma mensagem do kafka é muito grande para ser armazenado em apenas um local, deste modo sendo dividido para ser armazenados em diferentes locais.
Ao ser armazenado cada partição irá receber um número sequencial que irá simbolizar seu offset dentro daquele tópico.

## Funcionamento:
Kafka segue o padrão de projeto [Publisher-Subscriber](https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern).

Durante o ciclo de vida de uma aplicação utilizando kafka, producers irão criar e enviar mensagens as quais possuem um tópico específico para um servidor kafka e estas serão enfileiradas e ficarão lá, onde então os consumers interessados nestas mensagens irão pedir se existe alguma mensagem para eles e, com a ajuda do broker retira-los do servidor de acordo com o seu tópico e consumi-los. 

Caso o consumer falhe em algum momento ao consumir uma mensagem, ao se reerguer é possivel continuar do momento em que ele foi encerrado buscando o histórico que foi mantido dentro do broker.

Para identificarmos uma mensagem é necessário saber 3 coisas:
- Tópico: Assunto o qual definirá qual o tipo da mensagem para ser processada.
- Numero de partições: Quantidade de partes que foi dividido a mensagem.
- Offset: Qual a parte da partição ela é. 

## Stream API:
As Api's de Stream do Kafka ajuda a