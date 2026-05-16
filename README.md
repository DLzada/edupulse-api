# EduPulse API

EduPulse API é uma plataforma robusta de inteligência de dados voltada à análise, monitoramento e otimização da infraestrutura tecnológica e pedagógica de instituições de ensino. Desenvolvida inicialmente para suprir as demandas de mapeamento situacional e alocação de recursos em Picuí e regiões circunvizinhas, a arquitetura foi projetada de forma modular e escalável, permitindo sua aplicação e expansão imediata para qualquer município ou cenário regional do país.

A aplicação consolida indicadores complexos a partir de dados estruturados das escolas, permitindo que gestores públicos, secretarias de educação e tomadores de decisão identifiquem assimetrias estruturais, calculem índices automáticos de qualidade de infraestrutura e priorizem investimentos de forma estritamente analítica.

---

## 1. O Problema Real

A gestões públicas de educação frequentemente enfrentam desafios da assimetria de informações. Dados operacionais e estruturais — como conectividade em sala de aula, laboratórios disponíveis, recursos de acessibilidade e bibliotecas — costumam residir em relatórios descentralizados ou planilhas estáticas. Isso dificulta uma resposta rápida a problemas críticos.

O EduPulse foi concebido para mitigar essas dores através de três pilares:
* **Centralização e Consistência:** Substituição do controle manual por uma API REST estruturada sob regras estritas de integridade de dados (validações via Jakarta Validation e restrições de unicidade como o código INEP).
* **Auditoria de Infraestrutura Dinâmica:** Um algoritmo central traduz a presença ou ausência de recursos físicos em pontuações numéricas ponderadas (Infrastructure Score), categorizando o nível operacional de cada instituição.
* **Inteligência Geográfica e Estatística:** Consolidação automatizada de métricas por município, permitindo comparar colégios de forma cruzada e identificar quais unidades sofrem com déficits específicos de infraestrutura.

Embora sua criação tenha sido fundamentada para o município onde me resido, Picuí, também pode ser utilizado por municípios vizinhos com os próprios dados locais de minha cidade. O Edupulse Api tem independência de escopo podendo permitir o cadastro e o monitoramento geopolítico de qualquer malha escolar e qualquer região.

---

## 2. Tecnologias e Arquitetura

O ecossistema técnico foi selecionado para garantir alta performance, tipagem estrita e portabilidade em qualquer ambiente de implantação:

* **Ecossistema Principal:** Java 17 e Spring Boot 3
* **Persistência de Dados:** Spring Data JPA e Hibernate
* **Banco de Dados:** PostgreSQL (Implantado via contêineres Docker)
* **Conteneirização e Infraestrutura:** Docker e Docker Compose
* **Produtividade e Validação:** Project Lombok e Jakarta Validation
* **Documentação Interativa:** Springdoc OpenAPI / Swagger UI

> A arquitetura segue o padrão de camadas (Controller -> Service -> Repository -> Entity), isolando completamente as regras de negócio das estruturas de transporte de dados por meio do uso de Java Records (DTOs).

---

## 3. Funcionalidades Principais

* **Cálculo Automatizado de Score Escolar:** Cada escola cadastrada passa por uma matriz de pesagem baseada em seus recursos físicos. O score máximo é de 100 pontos, subdividido em:
    * Conectividade (Até 30 pontos): Internet e Wi-Fi dedicado para alunos.
    * Estrutura Pedagógica (Até 40 pontos): Biblioteca, laboratório de informática e laboratório de ciências.
    * Bem-estar e Inclusão (Até 30 pontos): Acessibilidade arquitetônica e quadra de esportes.
* **Classificação de Status Operacional:** Com base no Score Final, a API categoriza a escola automaticamente entre as faixas: "Crítica" (0 a 40), "Básica" (41 a 70) ou "Referência" (71 a 100).
* **Análise Comparativa Avançada:** Endpoint dedicado para cruzar os dados de duas instituições distintas (via código INEP), gerando um relatório dinâmico que destaca as vantagens estruturais de uma unidade sobre a outra.
* **Mapeamento de Déficits Setoriais:** Filtros avançados para isolar, dentro de uma determinada cidade, quais escolas não possuem um recurso específico (ex: exibir todas as escolas de Picuí que não possuem "computerlab").
* **Agregação Estatística Municipal:** Consolidação em tempo real do total de colégios, soma da densidade de estudantes, média geral de score da cidade e métricas percentuais de inclusão digital.
* **Exclusão Lógica (Soft Delete):** Preservação da integridade referencial histórica do banco de dados através do controle do estado lógico ativo/inativo das instituições, blindando as consultas contra deleções acidentais.

---

## 4. Estrutura de Endpoints (API Rest)

### Escolas (CRUD e Paginação)
* `GET /v1/schools` - Retorna todas as escolas ativas com paginação estruturada (`Pageable`).
* `GET /v1/schools/search` - Busca escolas por nome via correspondência parcial e insensível a maiúsculas/minúsculas (`ContainingIgnoreCase`). Mapeado com paginação.
* `GET /v1/schools/level/{level}` - Filtra colégios por nível de ensino (ex: `ENSINO_MEDIO`, `TECNICO`, `SUPERIOR`).
* `POST /v1/schools` - Cadastra uma nova escola. Validações estritas barram códigos INEP duplicados ou dados corrompidos.
* `PUT /v1/schools/{inepCode}` - Atualiza integralmente os dados operacionais de uma escola localizada pelo INEP.
* `DELETE /v1/schools/{inepCode}` - Executa o Soft Delete, desativando a escola para consultas gerais sem apagar o registro físico.