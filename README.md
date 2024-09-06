
# Documentação da API de Estoque

## URL Base
- A URL base para acessar a API é: `http://localhost:8080`

## Endpoints

### 1. **Listar Produtos**

- **Método:** GET
- **URL:** `/api/produtos`
- **Descrição:** Retorna uma lista de todos os produtos cadastrados.

#### Exemplo de Requisição:
```http
GET http://localhost:8080/api/produtos


#### Exemplo de Resposta (200 OK):
```json
[
  {
    "id": 1,
    "nome": "Produto A",
    "quantidade": 10
  },
  {
    "id": 2,
    "nome": "Produto B",
    "quantidade": 20
  }
]
```

### 2. **Criar Produto**

- **Método:** POST
- **URL:** `/produtos`
- **Headers:**
    - `Content-Type: application/json`
- **Descrição:** Cria um novo produto.

#### Exemplo de Requisição:
```http
POST http://localhost:8080/api/produtos
```

#### Body (raw, JSON):
```json
{
  "nome": "Produto A",
  "quantidade": 10
}
```

#### Exemplo de Resposta (201 Created):
```json
{
  "id": 1,
  "nome": "Produto A",
  "quantidade": 10
}
```

### 3. **Atualizar Produto**

- **Método:** PUT
- **URL:** `/produtos/{id}`
- **Headers:**
    - `Content-Type: application/json`
- **Descrição:** Atualiza as informações de um produto existente.

#### Exemplo de Requisição:
```http
PUT http://localhost:8080/api/produtos/1
```

#### Body (raw, JSON):
```json
{
  "nome": "Produto A Atualizado",
  "quantidade": 20
}
```

#### Exemplo de Resposta (200 OK):
```json
{
  "id": 1,
  "nome": "Produto A Atualizado",
  "quantidade": 20
}
```

### 4. **Deletar Produto**

- **Método:** DELETE
- **URL:** `/produtos/{id}`
- **Descrição:** Remove um produto existente.

#### Exemplo de Requisição:
```http
DELETE http://localhost:8080/api/produtos/1
```

#### Exemplo de Resposta (204 No Content):
Nenhum conteúdo retornado.

### 5. **Criar Movimentação**

- **Método:** POST
- **URL:** `/movimentacoes`
- **Headers:**
    - `Content-Type: application/json`
- **Descrição:** Cria uma nova movimentação de estoque (entrada ou saída).

#### Exemplo de Requisição:
```http
POST http://localhost:8080/api/movimentacoes
```

#### Body (raw, JSON):
```json
{
  "produtoId": 1,
  "quantidade": 5,
  "tipo": "Entrada"
}
```

#### Exemplo de Resposta (201 Created):
```json
{
  "id": 1,
  "produtoId": 1,
  "quantidade": 5,
  "tipo": "Entrada",
  "dataHora": "2024-09-02T12:34:56.789"
}
```

### 6. **Listar Movimentações**

- **Método:** GET
- **URL:** `/movimentacoes`
- **Descrição:** Retorna uma lista de todas as movimentações de estoque registradas.

#### Exemplo de Requisição:
```http
GET http://localhost:8080/api/movimentacoes
```

#### Exemplo de Resposta (200 OK):
```json
[
  {
    "id": 1,
    "produtoId": 1,
    "quantidade": 5,
    "tipo": "Entrada",
    "dataHora": "2024-09-02T12:34:56.789"
  },
  {
    "id": 2,
    "produtoId": 1,
    "quantidade": 3,
    "tipo": "Saída",
    "dataHora": "2024-09-02T13:00:00.000"
  }
]
```

## Deletar Movimentação
**Método: DELETE**

**URL:** ``/movimentacoes/{id}``

**Descrição:** Remove uma movimentação existente pelo id especificado e reverte a quantidade no estoque.

**Exemplo de Requisição:**
``DELETE http://localhost:8080/movimentacoes/1``

## Observações

- Certifique-se de que o servidor da aplicação está em execução antes de fazer as requisições.
- Se a aplicação estiver rodando em uma porta diferente de `8080`, atualize a URL base conforme necessário.
- No Postman, ao configurar o body como `raw`, selecione o formato `JSON` na lista suspensa à direita.



## Docker 
Com a imagem do Postgres no seu Docker execute o camando abaixo para subir o container junto ao projeto.
```sql
docker run --name dark-stock -p 5432:5432 -e POSTGRES_PASSWORD=231020 postgres -d -v C:\Users\JC INFO\Documents\postgres:/var/lib/postgressql/data