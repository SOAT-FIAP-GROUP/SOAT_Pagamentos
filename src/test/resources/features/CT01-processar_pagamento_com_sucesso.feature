Feature: Pagamentos
  Como usuário desejo realizar pagamentos via integração Mercado Pago

  @WebHook
  Scenario: Processar pagamento com sucesso
    Given que foi recebida uma mensagem no webhook indicando um pagamento efetuado
    When o servico invocar a funcao do processamento de pagamento
    Then a funcao deve ser executada com sucesso