let currentMonth = new Date().getMonth();
let currentYear = new Date().getFullYear();
let currentDay = new Date().getDate();
let changeCurrentMonth = new Date().getMonth();
let changeCurrentYear = new Date().getFullYear();
let selectedDayElement = null;

//// Function to call generate calendar on load
//window.onload = function () {
//    generateCalendar(currentMonth, currentYear);
//};
//
//// Function to generate the calendar
//function generateCalendar(month, year) {
//    const calendar = document.getElementById('calendar');
//    const monthYearDisplay = document.getElementById('month-year');
//    const calendarTextDisplay = document.getElementById('calendar-text');
//    calendar.innerHTML = ''; // Clear any existing content
//
//    // Array of month names
//    const monthNames = ["January", "February", "March", "April", "May", "June", 
//    "July", "August", "September", "October", "November", "December"];
//
//    // Update the month-year display
//    monthYearDisplay.textContent = `${monthNames[month]} ${year}`;
//    calendarTextDisplay.textContent = `${monthNames[month]}`
//
//    // Create a new Date object for the first and last day of the month
//    const firstDayOfMonth = new Date(year, month, 1);
//    const lastDayOfMonth = new Date(year, month + 1, 0);
//
//    // Calculate the day of the week of the first day of the month
//    const firstDayOfWeek = firstDayOfMonth.getDay();
//    const totalDays = lastDayOfMonth.getDate();
//
//    // Calculate the last day of the previous month
//    const lastDayOfPrevMonth = new Date(year, month, 0).getDate();
//
//    // Add blank div elements for the days before the first day of the month
//    for (let i = firstDayOfWeek; i > 0; i--) {
//        let prevMonthDay = document.createElement("div");
//        prevMonthDay.className = "calendar-day prev-month";
//        prevMonthDay.textContent = lastDayOfPrevMonth - i + 1;
//        calendar.appendChild(prevMonthDay);
//    }
//
//    // Add div elements for each day of the current month
//    for (let day = 1; day <= totalDays; day++) {
//        let daySquare = document.createElement("div");
//        daySquare.className = "calendar-day";
//        daySquare.id = `day-${day}`;
//        daySquare.onclick = () => { openDay(day) };
//    
//        // Create a div for the day text
//        let dayText = document.createElement("div");
//        dayText.className = "day-text";
//        dayText.textContent = day;
//        
//        // Append the day text div to the day square
//        daySquare.appendChild(dayText);
//    
//        // Highlight the current date
//        if (day === currentDay && month === currentMonth && year === currentYear) {
//            dayText.classList.add('current-date');
//        }
//    
//        calendar.appendChild(daySquare);
//    }
//
//    // Calculate the number of blank days needed after the last day of the month
//    const lastDayOfWeek = lastDayOfMonth.getDay();
//    const nextMonthDaysCount = 6 - lastDayOfWeek;
//
//    // Add blank div elements for the days after the last day of the month
//    for (let i = 1; i <= nextMonthDaysCount; i++) {
//        let nextMonthDay = document.createElement("div");
//        nextMonthDay.className = "calendar-day next-month";
//        nextMonthDay.textContent = i;
//        calendar.appendChild(nextMonthDay);
//    }
//}
//
//// Function to change the month
//function changeMonth(delta) {
//    changeCurrentMonth += delta;
//    if (changeCurrentMonth > 11) {
//        changeCurrentMonth = 0;
//        changeCurrentYear++;
//    } else if (changeCurrentMonth < 0) {
//        changeCurrentMonth = 11;
//        changeCurrentYear--;
//    }
//    generateCalendar(changeCurrentMonth, changeCurrentYear);
//}

function setMealId(svgButton) {
    const meal = svgButton.getAttribute('data-meal');
    const mealIdMap = {
        'Breakfast': 1,
        'Lunch': 2,
        'Brunch': 3,
        'Dinner': 4
    };

    const mealIdInput = document.getElementById('mealIdInput');
    mealIdInput.value = mealIdMap[meal];
}

function setMealType(meal) {
    document.getElementById('selectedMealType').value = meal;
}

const planForm = document.getElementById('planForm');

document.getElementById('returnBtn').addEventListener('click', (e) => {
    e.preventDefault();
    planForm.style.display = 'none';
});


document.addEventListener('DOMContentLoaded', () => {
    const settingSvg = document.querySelector('.setting-svg');
    const planControl = document.querySelectorAll('.plan-main-control');
    const addPlan = document.querySelectorAll('.add-svg');
    const updateSvg = document.querySelectorAll('.update-plan');
    const closeUpdateBtn = document.querySelectorAll('.returnUpdateBtn');
    const quantities = document.querySelectorAll('.menu-info-quantity');
    const saveBtn = document.getElementById('saveBtn');
    const deleteBtn = document.querySelectorAll('.deleteBtn');
    const saveUpdateBtn = document.querySelectorAll('.saveUpdateBtn');



    planControl.forEach(item => {
        settingSvg.addEventListener('click', () => {
            item.classList.toggle('hidden-box');
        });
    });

    // Function to find the parent element with a specific class
    function findParentWithClass(element, className) {
        while (element && !element.classList.contains(className)) {
            element = element.parentElement;
        }
        return element;
    }


    updateSvg.forEach((button) => {
        button.addEventListener('click', function () {
            const planControl = findParentWithClass(button, 'plan-control');
            if (planControl) {
                const aside = planControl.querySelector('aside');
                if (aside) {
                    aside.style.display = 'flex';
                }
            }
        });
    });

    closeUpdateBtn.forEach(button => {
        button.addEventListener('click', function (e) {
            const planControl = findParentWithClass(button, 'plan-control');
            if (planControl) {
                const aside = planControl.querySelector('aside');
                if (aside) {
                    e.preventDefault();
                    aside.style.display = 'none';
                }
            }
        });
    });


    addPlan.forEach(button => {
        button.addEventListener('click', (e) => {
            const mealType = e.currentTarget.getAttribute('data-meal');

            const planFormText = document.getElementById('plan-type-text');

            planFormText.textContent = mealType;

            planForm.style.display = 'flex';
        });
    });



    quantities.forEach(quantity => {
        const decrementButton = quantity.querySelector('.decrement');
        const incrementButton = quantity.querySelector('.increment');
        const quantityInput = quantity.querySelector('.input-quantity');

        decrementButton.addEventListener('click', (event) => {
            event.preventDefault();
            let currentValue = parseInt(quantityInput.value);
            if (!isNaN(currentValue) && currentValue > 1) {
                quantityInput.value = currentValue - 1;
            }
        });

        incrementButton.addEventListener('click', (event) => {
            event.preventDefault();
            let currentValue = parseInt(quantityInput.value);
            if (!isNaN(currentValue)) {
                quantityInput.value = currentValue + 1;
            }
        });
    });

// Selection button js
    const buttonPairs = document.querySelectorAll('.button-selection');

    buttonPairs.forEach(pair => {
        const mealKit = pair.querySelector('.meal-kit');
        const prepareMeal = pair.querySelector('.prepare-meal');

        mealKit.addEventListener('click', (e) => {
            e.preventDefault();
            toggleClass(mealKit, prepareMeal);
        });

        prepareMeal.addEventListener('click', (e) => {
            e.preventDefault();
            toggleClass(prepareMeal, mealKit);
        });
    });



    function toggleClass(button1, button2) {
        button1.classList.add('enable-selection');
        button2.classList.remove('enable-selection');
    }

    const buttons = document.querySelectorAll('.updateBtn');

    // Lặp qua từng button và thêm sự kiện click
    buttons.forEach(button => {
        button.addEventListener('click', function () {
            const selectedMealTypeInput = this.closest('.menu-info-selection').querySelector('.selectedMealTypeUpdate');

            selectedMealTypeInput.value = this.dataset.type;
        });
    });

    // save button when create new plan 
    saveBtn.addEventListener('click', (e) => {
        e.preventDefault();
        planForm.style.display = 'none';
        Swal.fire({
            position: "center",
            icon: "success",
            title: "Create Plan Successfully",
            showConfirmButton: false,
            timer: 1500,
            didClose: () => {
                document.getElementById('form-create').submit();
            }
        });
    });

    deleteBtn.forEach(button => {
        button.addEventListener('click', (e) => {
            e.preventDefault();
            const form = e.target.closest('.delete-plan');

            Swal.fire({
                title: "Are you sure?",
                text: "You won't be able to revert this!",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#81d34a",
                cancelButtonColor: "#d33",
                confirmButtonText: "Yes, delete it!"
            }).then((result) => {
                if (result.isConfirmed) {
                    form.submit();
                }
            });
        });
    });

    saveUpdateBtn.forEach(button => {
        button.addEventListener('click', (e) => {
            e.preventDefault();
            const form = e.target.closest('.form-update');
            const updateContainer = e.target.closest('.update-container');

            updateContainer.style.display = 'none';
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Update Plan Successfully",
                showConfirmButton: false,
                timer: 1500,
                didClose: () => {
                    form.submit();
                }
            });
        });
    });
});