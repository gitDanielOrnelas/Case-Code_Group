# 🚀 Projeto de Testes Automatizados

## 📌 Tecnologias Utilizadas

Aqui estão as versões das principais ferramentas utilizadas no projeto:

| 🔧 Tecnologia            | 🌟 Versão |
|-------------------------|----------|
| **Chromedriver**        | v134     |
| **Geckodriver**         | v0.36.0  |
| **JDK (Java Development Kit)** | 20.0.2 |
| **Cucumber-JVM**        | 7.6.0    |

## 📜 Sobre o Projeto
Este repositório contém testes automatizados para aplicações web, utilizando **Selenium WebDriver**, **JUnit**, **Cucumber**, com **Page Objects** entre outras ferramentas.

## 🛠️ Configuração do Ambiente

1. Instale o **JDK 20.0.2** e configure a variável de ambiente `JAVA_HOME`.
2. Baixe e adicione o **Chromedriver** (v134) e **Geckodriver** (v0.36.0) ao `PATH`.
3. Certifique-se de que a versão do **Cucumber-JVM** está corretamente instalada.
4. Basta executar o "RunCucumberTest.java"
5. CAPTCHA: Fique atento ao possível captcha da Amazon, coloquei um contorno na aplicação, onde descobri que clicando no link "Tentar uma imagem diferente", ele avança pra tela inicial. Não sei dizer se foi proposital da Amazon para aplicações automáticas, ou é se um BUG.

## 📂 Estrutura do Projeto

```Case-Code_Group/
│-- src/
│   ├── /test/java/..core  # coração do projeto, navegação, e métodos de apoio
│   ├── /test/java/..pages # Object Page da página inicial da Amazon
│   ├── /test/java/..runner # Executor do projeto
│   ├── /test/java/..steps # Steps das .features
│   ├── /test/java/..utilities # Itens utilitários, printscreen, documentação e afins
│-- features/..Desempenho_PaginaInicial.feature        # 3 cenários com intuito no desempenho, abrindo com Chrome(passed), com Firefox(passed) e "forçando" um carregamento demorado (fail)
│-- features/..Pesquisa_Produto.feature                # 3 cenários com intuito na barra de pesquisa, Chrome como padrão.
│-- features/..Responsividade_MenuInicial.feature      # 3 cenários com intuito na reponsividade da tela, Chrome como padrão.
│-- ├── /..       # Arquivos .feature
│-- pom.xml         # Configuração do Maven
│-- README.md       # Descritivo do Projeto
│-- target/cucumber-reports.html  # Relatório dos testes finalizados com printscreen da página final
│-- target/cucumber-reports/screenshots/*.png  # Screenshots das telas
```

📌 **Dúvidas ou sugestões?** Sinta-se à vontade para perguntar! 🚀
