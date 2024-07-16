// animation on scroll
const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => {
        console.log(entry)
        if(entry.isIntersecting){
            entry.target.classList.add('show');
        }
    });
});


const hiddenElement = document.querySelectorAll('.hidden');
hiddenElement.forEach((el) => observer.observe(el));



// faq
const radios = document.querySelectorAll('input[type="radio"][name="accordion"]');

radios.forEach(radio => {
  radio.addEventListener('click', function() {
    if (this.previousValue === this.value) {
      this.checked = false;
      this.previousValue = null;
    } else {
      this.previousValue = this.value;
    }
  });
});
