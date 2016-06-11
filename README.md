# maven-ejb3-servlet
Projeto demo que simula a utilização de todos tipos de TransactionAttributeType

Segue abaixo os tipos da TransactionAttributeType:

 * MANDATORY: O método será executado dentro dessa mesma transação. Caso o cliente chame o método fora de uma transação, um erro será lançado e o código do método não será executado.

 * NOT_SUPPORTED: O método sempre será executado fora de uma transação. Se o cliente estiver dentro de uma transação, essa é suspensa até que o método execute seu código e retorne.

 * REQUIRES_NEW: Independentemente do cliente estar rodando ou não dentro de uma transação, uma nova transação será criada para a execução desse método

 * SUPPORTS: Se o cliente chamar o método fora de uma transação, nenhuma será criada e o método será executado fora de uma transação. Se o cliente chamar dentro de uma transação, o método será executado dentro dessa mesma transação.

 * REQUIRED: Se um cliente invoca o método dentro de uma transação, o método será executado dentro desta transação. Se o método for invocado fora de uma transação, será criada uma nova transação.

 * NEVER: O método será executado normalmente sem criar nenhuma transação. Caso o cliente esteja envolvido em uma transação no momento da chamada, um erro será lançado e o código do método não será executado.
