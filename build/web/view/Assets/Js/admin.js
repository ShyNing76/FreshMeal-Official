$(document).ready(function () {
    // Add new step row
    $(document).on('click', '.btn-add-step', function () {
        var targetContainer = $(this).data('target');
        var stepCount = $(targetContainer).find('.form-group').length + 1;
        var newStep = `
            <div class="form-group">
                <div class="row">
                    <div class="col-md-2">
                        <input type="number" class="form-control" placeholder="Step ${stepCount}" name="step[]" value="${stepCount}" readonly />
                    </div>
                    <div class="col-md-8">
                        <input type="text" class="form-control" placeholder="Instruction for Step ${stepCount}" name="instruction[]" />
                    </div>
                    <div class="col-md-1">
                        <button type="button" class="btn btn-danger btn-remove-step"><i class="fas fa-minus"></i></button>
                    </div>
                </div>
            </div>
        `;
        $(targetContainer).append(newStep);
    });

    // Remove step row
    $(document).on('click', '.btn-remove-step', function () {
        $(this).closest('.form-group').remove();
        updateStepPlaceholders();
    });

    // Update step placeholders after removing a step
    function updateStepPlaceholders() {
        $('.recipe-container .form-group').each(function (index) {
            $(this).find('input[name="step[]"]').val(index + 1);
            $(this).find('input[name="step[]"]').attr('placeholder', 'Step ' + (index + 1));
            $(this).find('input[name="instruction[]"]').attr('placeholder', 'Instruction for Step ' + (index + 1));
        });
    }

    // Add new ingredient row
    $(document).on('click', '.btn-add-ingredient', function () {
        var targetContainer = $(this).data('target');
        var ingredientOptions = '';
        listIngredient.forEach(function (ingredient) {
            ingredientOptions += `<option value="${ingredient.id}">${ingredient.name}</option>`;
        });
        var newIngredient = `
            <div class="form-group">
                <div class="row">
                    <div class="col-md-4">
                        <select class="form-control" style="width: 100%" name="ingredientId[]">
                            <option value="">Ingredient</option>
                            ${ingredientOptions}
                        </select>
                    </div>
                    <div class="col-md-3">
                        <input type="text" class="form-control" placeholder="Quantity" name="ingredientQuantity[]" />
                    </div>
                    <div class="col-md-3">
                        <input type="text" class="form-control" placeholder="Unit" name="ingredientUnit[]" />
                    </div>
                    <div class="col-md-2">
                        <button type="button" class="btn btn-danger btn-remove-ingredient"><i class="fas fa-minus"></i></button>
                    </div>
                </div>
            </div>
        `;
        $(targetContainer).append(newIngredient);
    });

    // Remove ingredient row
    $(document).on('click', '.btn-remove-ingredient', function () {
        $(this).closest('.form-group').remove();
    });
});
