"use strict";
function addToCart(id) {
    let productImg = document.getElementById("img" + id);
    let imgSrc = productImg.getAttribute("src");
    let imgSrcString = String(imgSrc);
    let productName = document.getElementById("title" + id).innerText;
    let productPrice = document.getElementById("price" + id).innerText;
    productPrice = parseInt(productPrice.replaceAll(' ', '').slice(0, -1));
    let item = {
        id: id,
        img: imgSrcString,
        name: productName,
        price: productPrice,
        quantity: 1,
    };
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let existingItem = cart.find(cartItem => cartItem.name === item.name);
    if (existingItem) {
        existingItem.quantity += item.quantity;
    } else {
        cart.push(item);
    }
    localStorage.setItem('cart', JSON.stringify(cart));
    submitForm();
}

function fillCartItems() {
    let cartItemsElement = document.getElementById("cart-items");
    cartItemsElement.innerHTML = "";
    let summ = 0;
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    if (cart.length == 0){
        window.location.href = '/';
    }
    for (let ind = 0; ind < cart.length; ind++) {
        let cartItemElement = document.createElement('div');
        cartItemElement.classList.add('oneGood');
        cartItemElement.innerHTML = `
            <div class="card">
                <img src="${cart[ind].img}" alt="${cart[ind].name}">
                <p>${cart[ind].name}</p>
            </div>
            <div class="plus-minus">
            <i class="fa fa-minus" onclick="changeQuantity('${cart[ind].name}', -1, this)"></i>
            <p>${cart[ind].quantity}</p>
             <i class="fa fa-plus" onclick="changeQuantity('${cart[ind].name}', 1)"></i>
            </div>
            <p>${cart[ind].price} ₽</p>
           <div class = "removeAndPrice">
              <p>${cart[ind].quantity * cart[ind].price} ₽</p>
             <button class="remove-btn" onclick="removeItem(${ind})"><i class="fa fa-trash" aria-hidden="true"></i></button>
            </div>`;

                   let productIndex = document.getElementById('productIndex' + ind);
                   productIndex.value = parseInt(cart[ind].id);
           let cartName = document.getElementById('name' + ind);
           cartName.value = cart[ind].name;
           let cartQuantity = document.getElementById('quantity' + ind);
           cartQuantity.value = parseInt(cart[ind].quantity);
           let cartMeatPrice = document.getElementById('price' + ind);
           cartMeatPrice.value = parseInt(cart[ind].price);
           summ += cartQuantity.value * cartMeatPrice.value ;
        cartItemsElement.appendChild(cartItemElement);
    }
    let quantitySumm = document.getElementById('text2');
    quantitySumm.value = parseInt(summ | 0)
}
function removeItem(index) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    cart.splice(index, 1);
    localStorage.setItem('cart', JSON.stringify(cart));
    submitForm();
    fillCartItems();
}

// Функция для изменения количества товара
function changeQuantity(itemName, change, clickedElement) {
    const cartItems = JSON.parse(localStorage.getItem('cart')) || [];
    const cartItem = cartItems.find(item => item.name === itemName);
    const oldQuantity = cartItem.quantity;
    var totalPrice;
    if (cartItem) {
        // Проверяем, что количество товаров больше 1 перед уменьшением
        if (cartItem.quantity > 1 || change > 0) {
            cartItem.quantity += change;
        }else {
        // Если количество равно 1 и кнопка "минус" неактивна, завершаем выполнение функции
        return;
        }
        localStorage.setItem('cart', JSON.stringify(cartItems));
        fillCartItems();

        // Получаем кнопку "минус" из родительского элемента
        const minusButton = clickedElement.previousElementSibling;
        toggleMinusButton(cartItem.quantity, minusButton);
    }
}
// Включаем или выключаем кнопку "минус" в зависимости от количества товаров
function toggleMinusButton(quantity, minusButton) {
    if (quantity > 1) {
        minusButton.disabled = false;
    } else {
        minusButton.disabled = true;
    }
}

function removeAll(){
    localStorage.removeItem('cart');
    window.location.href = 'account';
}

function getNumberOfItems() {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let input = document.getElementById('inputItems');
    input.value = parseInt(cart.length);
}

function submitForm() {
    getNumberOfItems();
    var form = document.getElementById("myForm");
    form.submit();
}