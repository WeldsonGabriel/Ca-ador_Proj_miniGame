# 🏹 Caçada Selvagem

**Caçada Selvagem** é um mini game em Java, onde você assume o papel de um **caçador** que se aventura por ambientes aleatórios e enfrenta diferentes tipos de **animais selvagens**.  
Cada encontro é único — a vitória depende de estratégia, atributos e um pouco de sorte!

---

## 🧠 Conceito do Jogo

Você é um caçador em busca de desafios.  
A cada rodada:
- Um **ambiente aleatório** é gerado (floresta, montanha, deserto, etc.), com efeitos que alteram os atributos dos animais.
- Um **animal** aparece com idade, espécie e atributos próprios.
- Você decide se vai **enfrentar** ou **fugir**.
- Se vencer, ganha **XP** e pode **evoluir seus atributos** ao subir de nível.

Mas cuidado: se escolher o oponente errado, pode ser o fim da caçada! 💀

---

## ⚙️ Regras Básicas

| Tipo | Descrição |
|------|------------|
| **Caçador** | Possui atributos de **força**, **velocidade** e **inteligência**, que podem ser aprimorados ao subir de nível. |
| **Animal** | Cada animal tem uma **espécie** e uma **idade** (*Filhote*, *Adulto*, *Velho*) que afetam seus atributos. |
| **Ambiente** | Cada ambiente altera as condições da batalha com bônus ou penalidades. |
| **Combate** | O caçador vence se for superior em pelo menos **2 dos 3 atributos**. |
| **Evolução** | A cada 100 XP, o caçador sobe de nível e pode escolher um atributo para aprimorar. |

---

## 🌍 Tipos de Ambientes

| Ambiente | Efeito |
|-----------|---------|
| 🌲 **Floresta** | Aumenta velocidade dos animais |
| ⛰️ **Montanha** | Favorece força dos caçadores |
| 🏜️ **Deserto** | Favorece inteligência dos animais |
| 🐊 **Pântano** | Reduz velocidade de todos |

---

## 🦊 Animais

Espécies possíveis:
> `Lobo`, `Urso`, `Raposa`, `Águia`, `Cervo`, `Leopardo`

Cada um é gerado aleatoriamente com um estado:
- 🍼 **Filhote** — mais rápido, porém mais fraco  
- 💪 **Adulto** — equilibrado  
- 🧓 **Velho** — mais inteligente, porém lento

---

## 🧍‍♂️ Atributos do Caçador

| Atributo | Função |
|-----------|--------|
| **Força** | Influencia diretamente o combate físico |
| **Velocidade** | Pode garantir vantagem em fugas e emboscadas |
| **Inteligência** | Ajuda a identificar padrões e vencer animais mais estratégicos |

---

## 🕹️ Como Jogar

1. Clone o projeto:
   ```bash
   git clone https://github.com/seu-usuario/Ca-ador_Proj_miniGame.git
Abra o projeto no IntelliJ IDEA (ou qualquer IDE Java).

Estrutura recomendada:

css
Copiar código
src/
 ├── app/
 │     └── Main.java
 └── model/
       ├── Cacador.java
       ├── Animal.java
       └── Ambiente.java
Execute o arquivo:

css
Copiar código
app/Main.java
Jogue pelo terminal interativo!

🧩 Estrutura Modular
O código foi projetado para ser escalável e fácil de expandir:

Classe	Função
Cacador	Controla atributos, XP e evolução do jogador
Animal	Define comportamento e variação dos oponentes
Ambiente	Gera efeitos e modificadores aleatórios
Main	Controla o loop principal do jogo

✨ Essa arquitetura permite adicionar novos animais, ambientes, ou até sistemas de armas e itens sem reescrever o núcleo do jogo.

🚀 Próximos Passos (Evoluções Planejadas)
🪓 Sistema de armas e equipamentos

🌙 Modo noturno com efeitos adicionais

🏕️ Acampamentos e descanso entre caçadas

📊 Sistema de ranking e conquistas

🐾 Animais raros e lendários

💻 Tecnologias
Java 21+

Programação Orientada a Objetos (POO)

Lógica procedural com modularização

Execução via terminal

🧾 Licença
Este projeto é livre para fins educacionais e de aprendizado.
Créditos e menções são sempre bem-vindos! ❤️

🎯 Autor
Desenvolvido por Gabriel
📅 Versão inicial: Novembro de 2025
📍 Projeto conceitual - Caçada Selvagem (v1.0)
