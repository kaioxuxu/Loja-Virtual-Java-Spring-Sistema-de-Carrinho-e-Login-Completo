console.log("store.js carregado com sucesso!");

console.log("ola");
const userJson = sessionStorage.getItem("user");
const user = userJson ? JSON.parse(userJson) : null;

if (!user || !user.id) {
  console.error("Usuário não encontrado ou não logado.");
}

if (user) {
  document.getElementById("userName").innerText = user.nome || "Visitante";
  document.getElementById("userCpf").innerText = user.cpf || "N/A";
  document.getElementById("userEmail").innerText = user.email || "N/A";
}

const produtos = [
  { id: 1, nome: "Produto A", preco: 10.0 },
  { id: 2, nome: "Produto B", preco: 20.0 },
  { id: 3, nome: "Produto C", preco: 30.0 },
];

const prodDiv = document.getElementById("products");
produtos.forEach((p) => {
  const div = document.createElement("div");
  div.innerHTML = `
        <div style="border: 1px solid #ccccccff; padding: 10px; margin-bottom: 10px;">
            <h3>${p.nome}</h3>
            <p>R$ ${p.preco.toFixed(2)}</p>
            <button onclick="addToCart(${p.id})">Adicionar ao carrinho</button>
        </div>
    `;
  prodDiv.appendChild(div);
});

async function addToCart(prodId) {
  console.log("Enviando ao backend:", {
    userId: user.id,
    productId: prodId,
  });

  if (!user || !user.id) {
    alert("Erro: Usuário não logado.");
    return;
  }

  try {
    const res = await fetch("http://localhost:8080/api/cart/add", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ userId: user.id, productId: prodId }),
    });

    if (res.ok) {
      updateCartCount();
      alert("Item adicionado com sucesso!");
    } else {
      const errorText = await res.text();
      console.error(`Erro ao adicionar item: Status ${res.status}`, errorText);
      alert(`Erro ao adicionar item! Verifique o console: ${res.status}`);
    }
  } catch (error) {
    console.error("Falha na comunicação com a API:", error);
    alert("Erro de conexão ou CORS. Verifique se o servidor está rodando.");
  }
}

async function updateCartCount() {
  console.log("ta chegando aqui");
  if (!user || !user.id) return;

  try {
    const res = await fetch(
      `http://localhost:8080/api/cart/items?userId=${user.id}`
    );

    if (res.ok) {
      const items = await res.json();
      const totalQty = items.reduce((sum, it) => sum + (it.quantidade || 0), 0);

      const cartCountElement = document.getElementById("cartCount");
      if (cartCountElement) {
        cartCountElement.innerText = totalQty;
      }
    } else {
      console.error(`Falha ao obter carrinho: Status ${res.status}`);
      document.getElementById("cartCount").innerText = "?";
    }
  } catch (error) {
    console.error("Erro ao carregar carrinho (Network/CORS):", error);
    document.getElementById("cartCount").innerText = "X";
  }
}

document.addEventListener("DOMContentLoaded", updateCartCount);
console.log("store.js carregado com sucesso!");

const goToCartButton = document.getElementById("goToCartButton");
console.log("Botão encontrado?", goToCartButton);

if (goToCartButton) {
  goToCartButton.addEventListener("click", () => {
    console.log("Botão clicado! Redirecionando para cart.html...");
    window.location.href = "cart.html";
  });
} else {
  console.error(" Botão 'goToCartButton' não foi encontrado no DOM!");
}
