var valkcastellani = valkcastellani || {};

valkcastellani.TypedFieldControl = (function() {

    function TypedFieldControl() {
        this.fields = $('.typed');
    }

    TypedFieldControl.prototype.iniciar = function () {
        $(this.fields).each(function() {
            let typed_strings = $(this).getAttribute('data-typed-items')
            typed_strings = typed_strings.split(',')
            new Typed(this , {
                strings: typed_strings,
                loop: true,
                typeSpeed: 100,
                backSpeed: 50,
                backDelay: 2000
            });
        });
    }

    return TypedFieldControl;
})();

$(function() {
    var typedFieldControl = new valkcastellani.TypedFieldControl();
    typedFieldControl.iniciar();
});
