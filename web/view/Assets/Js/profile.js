//  Radio
// Function to clear checked state from all radios
function clearCheckedState() {
    const radios = document.querySelectorAll('.stardust-radio');
    radios.forEach(radio => {
        radio.classList.remove('radio-checked');
        radio.querySelector('.stardust-radio-button').classList.remove('radio-button-checked');
        radio.querySelector('.stardust-radio-button-outer__circle').classList.remove('radio-circle-checked');
    });
}

// Function to set checked state for a specific radio
function setCheckedState(radio) {
    radio.classList.add('radio-checked');
    radio.querySelector('.stardust-radio-button').classList.add('radio-button-checked');
    radio.querySelector('.stardust-radio-button-outer__circle').classList.add('radio-circle-checked');
}

// Add event listeners to all radio buttons
document.getElementById('radio-male').addEventListener('click', function () {
    clearCheckedState();
    setCheckedState(this);
});

document.getElementById('radio-female').addEventListener('click', function () {
    clearCheckedState();
    setCheckedState(this);
});

document.getElementById('radio-others').addEventListener('click', function () {
    clearCheckedState();
    setCheckedState(this);
});


// Date of birth
document.addEventListener("DOMContentLoaded", function () {
    const dayItem = document.getElementById('day');
    const monthItem = document.getElementById('month');
    const yearItem = document.getElementById('year');

    // Hàm tạo popover-item
    function createPopoverItem(start, end, parent) {
        const popoverItem = document.createElement('div');
        popoverItem.classList.add('popover-item');
        popoverItem.setAttribute('aria-role', 'tooltip');
        popoverItem.style.position = 'absolute';
        popoverItem.style.top = '42px';
        popoverItem.style.left = '0px';
        popoverItem.style.width = '120.25px';
        popoverItem.style.zIndex = '600';

        const ul = document.createElement('ul');
        ul.classList.add('popover-item-ul');
        for (let i = start; i <= end; i++) {
            const li = document.createElement('li');
            li.classList.add('popover-item-li');
            li.addEventListener('click', () => {
                const spanDate = parent.querySelector('.date');
                spanDate.textContent = i;
                hidePopoverItem(parent);
            });
            const div = document.createElement('div');
            div.textContent = i;
            li.appendChild(div);
            ul.appendChild(li);
        }
        popoverItem.appendChild(ul);

        parent.appendChild(popoverItem);

        return popoverItem;
    }

    // Hàm tắt popover-item
    function hidePopoverItem(parent) {
        const popoverItem = parent.querySelector('.popover-item');
        if (popoverItem) {
            popoverItem.remove();
        }
    }

    // Sự kiện click vào ngày
    dayItem.addEventListener('click', () => {
        const popoverItem = document.querySelector('#day .popover-item');
        if (!popoverItem) {
            createPopoverItem(1, 31, dayItem);
        } else {
            hidePopoverItem(dayItem);
        }
    });

    // Sự kiện click vào tháng
    monthItem.addEventListener('click', () => {
        const popoverItem = document.querySelector('#month .popover-item');
        if (!popoverItem) {
            createPopoverItem(1, 12, monthItem);
        } else {
            hidePopoverItem(monthItem);
        }
    });

    // Sự kiện click vào năm
    yearItem.addEventListener('click', () => {
        const popoverItem = document.querySelector('#year .popover-item');
        if (!popoverItem) {
            createPopoverItem(1900, new Date().getFullYear(), yearItem);
        } else {
            hidePopoverItem(yearItem);
        }
    });
});

function submitForm() {
    document.getElementById('imageUploadForm').submit();
}

function handleFileSelect(event) {
    const fileInput = event.target;
    const file = fileInput.files[0];
    if (file) {
        submitForm();
    }
}

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('input-file').addEventListener('change', handleFileSelect);
});