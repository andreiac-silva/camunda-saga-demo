# Demo de Saga Coreografada usando Camunda e Spring Boot - Choreography-based Saga demo Using Camunda and Spring Boot

Demo preparada para apresentação: "A Saga da consistência de dados entre microservices"

Neste exemplo, temos três microservices simples:

Services:
  - fidelity-customer-service
  - fidelity-order-service
 
SEC Service (Saga Execution Coordinator):
  - fidelity-order-service-sec
 
Para facilitar, os microservices utilizam a base de dados H2, que funciona in-memory.

No serviço de Customer, existe um script pré definido, dentro da pasta resources, onde insero um Customer assim que a aplicação é iniciada. Um atributo importante é o "numberOfPoints". Esses serviços, juntos, formam uma parte de uma aplicação onde o Customer possui pontos. Uma Order pode ser aberta pelo cliente e então, verificamos se o mesmo possui a quantidade de pontos necessária para que a Order seja aprovada. Os passos iniciais são:

  - Criar uma Order com status PENDING
  - Verificar no serviço de Customer se o cliente possui os pontos necessários para que a Order seja aprovada
    - Debita do saldo do cliente e o status da Order é alterado para APPROVED
    - O saldo do cliente continua o mesmo mas o status da Order é alterado para CANCELLED
  
Neste caso, possuímos uma Saga com apenas dois participantes (fidelity-customer-service e fidelity-order-service) e trẽs passos. 

São aplicações Spring Boot, então faça o deploy dos três serviços. A porta de entrada é um endpoint no serviço fidelity-order-service-sec: http://localhost:4007/orders-sec/. Não se esqueça de enviar no body o json com os atributos. Ex:

{
	"customerId": 1,
	"totalPoints": 20
}

Você pode verificar os Customers e Orders inseridos pelo console do H2:
  - http://localhost:4008/console
  - http://localhost:4009/console
