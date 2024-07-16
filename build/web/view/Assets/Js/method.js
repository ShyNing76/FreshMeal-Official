document.addEventListener("DOMContentLoaded", function() {
    const methodContainers = document.querySelectorAll(".method-item");

    methodContainers.forEach(container => {
        container.addEventListener("click", function() {
            // Remove checked class from all radio buttons
            methodContainers.forEach(item => {
                const radioButton = item.querySelector(".stardust-radio");
                radioButton.classList.remove("radio-checked");
                radioButton.querySelector(".stardust-radio-button").classList.remove("radio-button-checked");
                radioButton.querySelector(".stardust-radio-button-outer__circle").classList.remove("radio-circle-checked");
            });

            // Add checked class to the clicked radio button
            const radioButton = container.querySelector(".stardust-radio");
            radioButton.classList.add("radio-checked");
            radioButton.querySelector(".stardust-radio-button").classList.add("radio-button-checked");
            radioButton.querySelector(".stardust-radio-button-outer__circle").classList.add("radio-circle-checked");
        });
    });
});