# 🏹 Caçada Selvagem — Versão 2.0

**Caçada Selvagem** é um mini game em **Java**, onde você assume o papel de um **caçador** que explora ambientes aleatórios, enfrenta **animais selvagens** com diferentes níveis e atributos, e coleta **itens raros** deixados por criaturas épicas.  
Cada rodada é única — o sucesso depende de **estratégia, atributos e um toque de sorte!** 🍀

---

## 🧠 Conceito do Jogo

Você é um caçador em busca de desafios e recompensas.  
A cada rodada:

- Um **ambiente aleatório** é gerado (floresta, montanha, deserto, pântano, etc.), com efeitos próprios.  
- Um **animal** surge com espécie, idade e nível únicos.  
- Você decide se vai **enfrentar**, **fugir**, ou **explorar** mais o ambiente.  
- Ao vencer, o caçador ganha **XP**, pode **upar** seus atributos e **coletar equipamentos raros**.

Mas cuidado: ambientes hostis e animais lendários podem acabar com sua jornada rapidamente! 💀

---

## ⚙️ Mecânica do Sistema

| Tipo | Descrição |
|------|------------|
| **Caçador** | Possui atributos de **força**, **velocidade** e **inteligência**, além de um **nível** e **inventário de equipamentos**. |
| **Animal** | Cada espécie possui variações por idade e nível, afetando seus atributos base e chance de drop. |
| **Ambiente** | Define modificadores globais (como bônus de força ou penalidades de agilidade). Ambientes raros podem conter **BOSSes**. |
| **Item** | Equipamentos e artefatos podem ser encontrados em ambientes épicos ou dropados por animais lendários. |
| **XP e Níveis** | A cada vitória, o jogador ganha XP proporcional à dificuldade do inimigo e ao ambiente. A progressão é não-linear e adaptativa. |

---

## 🌍 Tipos de Ambientes

| Ambiente | Efeito | Raridade |
|-----------|---------|-----------|
| 🌲 **Floresta** | Aumenta velocidade dos animais | Comum |
| ⛰️ **Montanha** | Favorece força dos caçadores | Comum |
| 🏜️ **Deserto** | Favorece inteligência dos animais | Incomum |
| 🐊 **Pântano** | Reduz velocidade de todos | Raro |
| 🌋 **Vulcão** | Ambientes extremos com chance de drop lendário | Épico |

---

## 🦊 Animais Selvagens

Espécies base:  
> `Lobo`, `Urso`, `Raposa`, `Águia`, `Cervo`, `Leopardo`, `Tigre`, `Onça`, `Cobra`, `Javali`

Cada um possui um **estado**:

| Estado | Efeito |
|---------|---------|
| 🍼 **Filhote** | Rápido, mas fraco e impulsivo |
| 💪 **Adulto** | Equilibrado, oponente padrão |
| 🧓 **Velho** | Mais inteligente e resistente, porém lento |
| 🔥 **Lendário (BOSS)** | Atributos amplificados e chance de drop de item raro |

---

## ⚔️ Sistema de Itens

Os itens possuem tipos e níveis. Cada tipo adiciona bônus diretos aos atributos do caçador.

| Tipo | Efeito | Drop |
|------|---------|------|
| 🪓 **Arma** | +Força | Animais Lendários |
| 🛡️ **Armadura** | +Resistência | Bosses e Ambientes Raros |
| 🎯 **Amuleto** | +Inteligência | Ambientes Épicos |
| 🥾 **Botas** | +Velocidade | Animais Raros |
| 💎 **Artefato** | Bônus geral e especial | Drop único de Boss |

Limite de itens: 5 por tipo — cada um com níveis independentes.

---

## 🧍‍♂️ Atributos do Caçador

| Atributo | Função |
|-----------|--------|
| **Força** | Aumenta poder de ataque e resistência física |
| **Velocidade** | Melhora chance de escapar ou atacar primeiro |
| **Inteligência** | Afeta decisões estratégicas e bônus de XP |
| **Resistência** | Reduz penalidades de ambientes hostis |
| **Sorte** | Aumenta chance de encontrar ambientes e itens raros |

---

## 🕹️ Como Jogar

### 1️⃣ Clone o projeto:
```bash
git clone https://github.com/WeldsonGabriel/Ca-ador_Proj_miniGame.git
