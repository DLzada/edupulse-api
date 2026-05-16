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