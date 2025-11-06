🏹 Caçada Selvagem — Versão 2.1

Caçada Selvagem é um mini game em Java, onde você assume o papel de um caçador que explora ambientes aleatórios, enfrenta animais selvagens com diferentes níveis e atributos, e coleta itens raros deixados por criaturas épicas.
Agora com batalhas animadas, mecânica de roubo de vida e reinício automático após a derrota, oferecendo uma jogabilidade mais imersiva e fluida! 🎮🔥

🧠 Conceito do Jogo

Você é um caçador em busca de desafios e recompensas.
A cada rodada:

Um ambiente aleatório é gerado (floresta, montanha, deserto, pântano, etc.), com efeitos próprios.

Um animal surge com espécie, idade e nível únicos.

Você entra em batalhas em tempo real, acompanhando o status e os danos na tela.

A cada vitória, o caçador ganha XP, pode upar seus atributos e coletar equipamentos raros.

Caso perca a batalha, sofre dano permanente — e a aventura recomeça automaticamente até que toda a vida seja perdida.

⚙️ Novas Mecânicas — Versão 2.1
Mecânica	Descrição
⚔️ Batalha Animada	Acompanhe o combate com exibição dinâmica de ações e status em tempo real.
❤️ Roubo de Vida (Sustain)	Parte do dano causado é convertido em HP — essencial para sobrevivência em longas caçadas.
💀 Sistema de Derrota e Reinício	O caçador não morre instantaneamente; ele perde parte da vida total e pode continuar até o HP zerar.
💬 StatusExibidor Refeito	Agora exibe mensagens animadas e coloridas, destacando eventos críticos e drops raros.
🔁 Loop de Jogo Contínuo	Após cada batalha, o jogo continua automaticamente, mantendo o ritmo da aventura.
🌍 Tipos de Ambientes
Ambiente	Efeito	Raridade
🌲 Floresta	Aumenta velocidade dos animais	Comum
⛰️ Montanha	Favorece força dos caçadores	Comum
🏜️ Deserto	Favorece inteligência dos animais	Incomum
🐊 Pântano	Reduz velocidade de todos	Raro
🌋 Vulcão	Ambientes extremos com chance de drop lendário	Épico
🦊 Animais Selvagens

Espécies base:

Lobo, Urso, Raposa, Águia, Cervo, Leopardo, Tigre, Onça, Cobra, Javali

Cada um possui um estado:

Estado	Efeito
🍼 Filhote	Rápido, mas fraco e impulsivo
💪 Adulto	Equilibrado, oponente padrão
🧓 Velho	Mais inteligente e resistente, porém lento
🔥 Lendário (BOSS)	Atributos amplificados e chance de drop de item raro
⚔️ Sistema de Itens
Tipo	Efeito	Drop
🪓 Arma	+Força	Animais Lendários
🛡️ Armadura	+Resistência	Bosses e Ambientes Raros
🎯 Amuleto	+Inteligência	Ambientes Épicos
🥾 Botas	+Velocidade	Animais Raros
💎 Artefato	Bônus geral e especial	Drop único de Boss

Limite de itens: 5 por tipo, cada um com níveis e bônus independentes.

🧍‍♂️ Atributos do Caçador
Atributo	Função
Força	Aumenta poder de ataque e resistência física
Velocidade	Melhora chance de escapar ou atacar primeiro
Inteligência	Afeta decisões estratégicas e bônus de XP
Resistência	Reduz penalidades de ambientes hostis
Sorte	Aumenta chance de encontrar ambientes e itens raros
Roubo de Vida	Recupera parte do HP a cada ataque bem-sucedido
🕹️ Como Jogar
1️⃣ Clone o projeto:
git clone https://github.com/WeldsonGabriel/Ca-ador_Proj_miniGame.git

2️⃣ Compile e execute:
cd Ca-ador_Proj_miniGame
javac -d bin src/**/*.java
java -cp bin Main

3️⃣ Acompanhe o jogo:

Durante a execução, os status são exibidos dinamicamente no console:

⚔️ Dano causado e recebido

❤️ Roubo de vida e regeneração

💬 Drops, níveis e mensagens de status coloridas

🔁 Reinício automático quando o HP total é zerado

📁 Estrutura do Projeto
src/
 ├─ main/                  # Arquivo principal do jogo
 ├─ model/
 │   ├─ personagem/        # Caçador, Animal e classes base
 │   ├─ ambiente/          # Tipos de ambiente e efeitos
 │   └─ item/              # Itens e equipamentos
 ├─ util/
 │   ├─ StatusExibidor.java  # Exibição animada e feedback visual
 │   └─ TextoFormatador.java # Formatação de mensagens coloridas
 └─ batalha/
     └─ Batalha.java        # Mecanismo de combate com animações e roubo de vida

🚀 Futuras Atualizações

🎮 Interface visual com JavaFX

🧩 Sistema de progressão com árvore de talentos

🐉 Eventos aleatórios e bosses sazonais

💾 Salvamento automático de progresso

🧑‍💻 Autor

Weldson Gabriel
Desenvolvido com Java puro ☕ e muita criatividade 🎯
