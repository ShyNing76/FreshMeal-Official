/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.addEventListener('DOMContentLoaded', (e) => {
    document.getElementById('addBtn').addEventListener('click', (e) => {
        e.preventDefault();

        const form = document.getElementById('addForm');
        const name = form.dataset.name;
        Swal.fire({
            position: "center",
            icon: "success",
            title: `Create ${name} Successfully`,
            showConfirmButton: false,
            timer: 1500,
            didClose: () => {
                form.submit();
            }
        });
    });

    document.querySelectorAll('.updateBtn').forEach(button => {
        button.addEventListener('click', (e) => {
            e.preventDefault();
            const form = button.closest('.updateForm');
            const name = form.dataset.name;

            Swal.fire({
                position: "center",
                icon: "success",
                title: `Update ${name} Successfully`,
                showConfirmButton: false,
                timer: 1500,
                didClose: () => {
                    form.submit();
                }
            });
        });
    });

    document.querySelectorAll('.statusBtn').forEach(button => {
        button.addEventListener('click', (e) => {
            e.preventDefault();
            const form = e.target.closest('.statusForm');
            const action = form.querySelector('input[name="action"]').value;
            const name = form.dataset.name;

            let title, text, confirmButtonText;
            if (action == '0') {
                title = `Are you sure you want to block ${name}?`;
                text = `${name} will be blocked and won't be able to access the platform.`;
                confirmButtonText = "Yes, block it!";
            } else {
                title = `Are you sure you want to unblock ${name}?`;
                text = `${name} will regain access to the platform.`;
                confirmButtonText = "Yes, unblock it!";
            }

            Swal.fire({
                title: title,
                text: text,
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#81d34a",
                cancelButtonColor: "#d33",
                confirmButtonText: confirmButtonText
            }).then((result) => {
                if (result.isConfirmed) {
                    form.submit();
                }
            });
        });
    });
});


document.addEventListener('DOMContentLoaded', (event) => {
    document.querySelectorAll('.orderBtn').forEach(button => {
        button.addEventListener('click', (e) => {
            e.preventDefault();
            const form = button.closest('.orderForm');
            const orderId = form.dataset.orderid;
            const status = form.dataset.status;

            let title, text, confirmButtonText;
            if (status === 'Approve') {
                title = `Are you sure you want to approve order #${orderId}?`;
                text = "This will change the order status to Approved.";
                confirmButtonText = "Yes, approve it!";
            } else if (status === 'Delivery') {
                title = `Are you sure you want to set order #${orderId} for delivery?`;
                text = "This will change the order status to Delivery.";
                confirmButtonText = "Yes, deliver it!";
            } else if (status === 'Finish') {
                title = `Are you sure you want to finish order #${orderId}?`;
                text = "This will change the order status to Finished.";
                confirmButtonText = "Yes, finish it!";
            } else if (status === 'Return/Refund') {
                title = `Are you sure you want to accept return/refund for order #${orderId}?`;
                text = "This will change the order status to Return/Refund.";
                confirmButtonText = "Yes, accept it!";
            }

            Swal.fire({
                title: title,
                text: text,
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#81d34a",
                cancelButtonColor: "#d33",
                confirmButtonText: confirmButtonText
            }).then((result) => {
                if (result.isConfirmed) {
                    form.submit();
                }
            });
        });
    });
});





