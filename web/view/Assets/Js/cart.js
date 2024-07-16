document.addEventListener('DOMContentLoaded', () => {
    const cartBoxes = document.querySelectorAll('.cart-box');

    cartBoxes.forEach(cartBox => {
        const decrementButton = cartBox.querySelector('.decrement');
        const incrementButton = cartBox.querySelector('.increment');
        const quantityInput = cartBox.querySelector('.quantity-cart input');
        const form = cartBox.querySelector('.update-quantity-form');

        decrementButton.addEventListener('click', (event) => {
            event.preventDefault();
            let currentValue = parseInt(quantityInput.value);
            if (!isNaN(currentValue) && currentValue > 1) {
                quantityInput.value = currentValue - 1;
                form.submit();
            }
        });

        incrementButton.addEventListener('click', (event) => {
            event.preventDefault();
            let currentValue = parseInt(quantityInput.value);
            if (!isNaN(currentValue)) {
                quantityInput.value = currentValue + 1;
                form.submit();
            }
        });
    });
});