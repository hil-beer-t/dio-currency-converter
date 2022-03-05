
![Dio logo](./img/dio_logo.svg)


#  Explorando Padrões de Projetos na Prática com Java - GFT Start #4 Java DIO Bootcamp

## Descrição do Desafio

Crie uma solução que explore o conceito de Padrões de Projeto na pŕatica. Para isso, você pode reproduzir um dos projetos que criamos durante as aulas ou, caso se sinta preparado, desenvolver uma nova ideia do zero ;-)




## Conversor de moedas

`Free currency converter 💰.`

`Conversor de moedas + CRUD de clientes. Uma aplicação que explora o conceito de Padrões de Projeto.`

![Capturar3](https://user-images.githubusercontent.com/52302576/156856491-5e766506-60bc-4c08-af5e-12dbc10a15aa.PNG)




## Aprendizados

* Feign
* Design Patterns usados no Spring
* Melhorando a qualidade de leitura do controlador usando Facade Pattern
* Conheci e fiz uso de outros padrões como Proxy e Observer

### Antes e depois

Contralador sem Facade

![Capturar](https://user-images.githubusercontent.com/52302576/156830164-fa11e666-71fc-46c5-838f-003a23ea3474.PNG)

Controlador com Facade

![Capturar2](https://user-images.githubusercontent.com/52302576/156830272-92b2f6bc-9c65-4ab5-84ea-090ea92c0d2e.PNG)





## Endpoints

[SwaggerHub](https://app.swaggerhub.com/apis/hil-beer-t/currency-converter/0.0.1)

#### Converte, na cotação atual, as moedas dadas

```http
  GET /currency/{currencyCode}?amount={amount}
```

| Parâmetro   | Tipo       | Descrição                           |  Exemplo
| :---------- | :--------- | :---------------------------------- |:---------
| `currencyCode` | `string` | Moedas suportadas: USD, BRL e EUR | BRL-USD
| `amount` | `double` | Total a ser convertido | 50



## API externa

[Documentação](https://docs.awesomeapi.com.br/api-de-moedas) da api externa utilizada.


## Licença

[MIT](https://choosealicense.com/licenses/mit/)


## Autores

- [@hil_beer_t](https://www.github.com/hil-beer-t)


## Agradecimentos

- Professor [@FalvoJr](https://github.com/falvojr)
