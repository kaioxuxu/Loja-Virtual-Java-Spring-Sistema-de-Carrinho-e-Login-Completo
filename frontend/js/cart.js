const user = JSON.parse(sessionStorage.getItem("user"));
document.getElementById("userName").innerText = user.nome;
async function loadCart() {
  const res = await fetch(`http://localhost:8080/api/cart/items?userId=${user.id}`);
  const items = await res.json();
  const list = document.getElementById("cartList");
  list.innerHTML = "";
  items.forEach((it) => {
    const li = document.createElement("li");
    li.innerText = `${it.product.nome} - Quantidade: ${it.quantidade}`;
    list.appendChild(li);
  });
}
loadCart();
