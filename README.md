# TORRES ERP — Sistema de Gestão para pequenas empresas

**TORRES ERP** é um sistema web completo, desenvolvido com foco na automação da gestão de um comércio . Ele permite controlar produtos, fornecedores,
compras e vendas de forma simples, funcional e personalizada para o dia a dia do pequeno empreendedor.

O sistema é dividido em duas partes:
- ⚙️ **Backend**: Java com Spring Boot
- 🌐 **Frontend**: HTML,  CSS e JavaScript puro

---

## 🚀 Funcionalidades atuais

- CRUD de produtos (nome, descrição, valores, código de barras etc.)
- CRUD de fornecedores
- Cadastro de compras
- Registro de vendas
- Integração entre frontend e backend via API REST

---

## 📌 Funcionalidades futuras (em desenvolvimento)

- 📊 Dashboard com indicadores (lucro, volume de vendas etc.)
- 📈 Curva ABC de produtos
- 📦 Controle de estoque em tempo real
- 🔒 Autenticação de usuários e controle de acesso

---

## 🛠️ Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- HTML + CSS + JavaScript (puro)
- Git + GitHub
- Google Cloud (VM Linux para hospedagem)

---

## 🌐 Acesso ao sistema (em produção)

> 🔗 http://35.233.132.93


---

## 📁 Como rodar o projeto localmente

### Backend (Java + Spring Boot)

```bash
# Clone o repositório
git clone https://github.com/eder1junio/TORRES_ERP/

# Vá até a pasta do backend
cd backend

# Execute o projeto com Maven
./mvnw spring-boot:run
