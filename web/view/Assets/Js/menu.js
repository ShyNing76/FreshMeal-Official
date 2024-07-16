//function getWeekRange(date){
//    const firstDay = new Date(date);
//      const lastDay = new Date(date);
//      
//      firstDay.setDate(date.getDate() - date.getDay() + 1); // Thứ hai của tuần hiện tại
//      lastDay.setDate(firstDay.getDate() + 6); // Chủ nhật của tuần hiện tại
//
//      const formatOptions = { day: '2-digit', month: '2-digit' };
//
//      const formattedFirstDate = firstDay.toLocaleDateString('vi-VN', formatOptions);
//      const formattedLastDate = lastDay.toLocaleDateString('vi-VN', formatOptions);
//
//      return `${formattedFirstDate} - ${formattedLastDate}`;
//}
//
//function updateWeekLinks() {
//    const links = document.querySelectorAll('.weekly-nav a');
//    const today = new Date();
//
//    links.forEach((link, index) => {
//      const startDate = new Date(today);
//      startDate.setDate(today.getDate() + (index * 7));
//      const weekRange = getWeekRange(startDate);
//      const monthName = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec"];
//
//
//      const [start, end] = weekRange.split(' - ');
//      const [startDay, startMonth] = start.split('-');
//      const [endDay, endMonth] = end.split('-');
//
//      const indexMonth = parseInt(startMonth) - 1;
//      
//      link.querySelector('small').textContent = monthName[indexMonth];
//      link.querySelector('span').textContent = `${startDay}-${endDay}`;
//    });
//  }
//
//document.addEventListener('DOMContentLoaded', updateWeekLinks);

document.addEventListener('DOMContentLoaded', function() {
  const filterLinks = document.querySelectorAll('.filter-link');

  // Lặp qua các phần tử filter-link
  filterLinks.forEach(link => {
    // Xử lý sự kiện click cho từng link
    link.addEventListener('click', function(event) {
      event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ a
       
      const group = this.getAttribute('data-group');
      const type = this.getAttribute('data-type');

      // Loại bỏ class 'selected' từ tất cả các link trong cùng nhóm
      filterLinks.forEach(otherLink => {
        if (otherLink.getAttribute('data-group') === group) {
          otherLink.classList.remove('selected');
          // Loại bỏ class 'selected' từ các phần tử có lớp là 'radio-circle'
          const radioCircle = otherLink.querySelector('.radio-circle');
          if (radioCircle) {
            radioCircle.classList.remove('selected');
          }
        }
      });

      // Thêm class 'selected' cho link hiện tại và phần tử 'radio-circle'
      this.classList.add('selected');
      const radioCircle = this.querySelector('.radio-circle');
      if (radioCircle) {
        radioCircle.classList.add('selected');
      }

      // Xử lý chuyển hướng đến trang mục tiêu với các tham số query string
      const url = new URL(this.href);
      url.searchParams.set('group', group);
      url.searchParams.set('type', type);
      window.location.href = url.toString();
    });

    // Kiểm tra nếu URL hiện tại có các tham số query, thì áp dụng class 'selected'
    const currentUrl = new URL(window.location.href);
    const currentGroup = currentUrl.searchParams.get('group');
    const currentType = currentUrl.searchParams.get('type');
    if (currentGroup && currentType) {
      if (link.getAttribute('data-group') === currentGroup && link.getAttribute('data-type') === currentType) {
        link.classList.add('selected');
        const radioCircle = link.querySelector('.radio-circle');
        if (radioCircle) {
          radioCircle.classList.add('selected');
        }
      }
    }
  });
});

