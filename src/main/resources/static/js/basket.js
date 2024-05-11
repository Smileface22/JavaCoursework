"use strict";
function addToCartFromInfoDiv(button) {
    var parentElement = button.closest('.product');
    // Получаем значения из элементов
    var h3Value = parentElement.querySelector('.prodName').textContent;
    var descriptionValue = parentElement.querySelector('.description p').textContent;
    var priceValue = parentElement.querySelector('.prodPrice').textContent.replace('₽', '');
    var imageSrcValue = parentElement.querySelector('img').getAttribute('src');
    // Вызываем функцию addToCart с полученными значениями
    addToCart(imageSrcValue, h3Value, descriptionValue, priceValue);
}

function addToCart(itemImage, itemName, itemDescription, itemPrice) {
    // Получаем информацию о товаре
    var cartItem = {
        image: itemImage,
        name: itemName,
        description: itemDescription,
        price: itemPrice,
        quantity: 1
    };

    // Получаем текущий массив корзины из localStorage
    var cartItems = JSON.parse(localStorage.getItem('cartGoods')) || [];

    // Проверяем, есть ли уже такой товар в корзине
    const existingItem = cartItems.find(item => item.name === cartItem.name);

    if (!existingItem) {
        cartItems.push(cartItem);
    }
    // Сохраняем обновленную корзину в localStorage
    localStorage.setItem('cart', JSON.stringify(cartItems));
}
