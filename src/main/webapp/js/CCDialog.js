/**
 * Created by chenxianjun on 16/1/2.
 */
!(function ($) {
    function CCDialog(option){
        var that=this;
        option.width=option.width||600;
        this.container=option.container||$('body');
        this.ok=option.ok||function(){};
        this.cancel=option.cancel||function(){};
        this.modalTemplate='<div class="modal fade" id="CCModal" tabindex="-1" role="dialog">' +
            '<div class="modal-dialog" role="document" style="width: '+option.width+'px">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span>×</span></button>' +
            '<h4 class="modal-title">'+option.title+'</h4>' +
            '</div>' +
            '<div class="modal-body">'+option.content+'</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default cc-cancel" data-dismiss="modal">'+option.cancelValue+'</button>' +
            '<button type="button" class="btn btn-primary cc-ok" data-dismiss="modal">'+option.okValue+'</button>' +
            '</div></div></div></div>';
    }
    CCDialog.prototype={
        click: function (e) {
            var target=$(e.target);
            if(target.is($('.modal-footer button.cc-cancel'))){
                this.cancel();
            }else if(target.is($('.modal-footer button.cc-ok'))){
                this.ok();
            }
        },
        destroy: function () {
            $('#CCModal').length&&$('#CCModal').remove();
        },
        showModal: function () {
            this.destroy();
            var dialog=$(this.modalTemplate)
                .appendTo(this.container)
                .on({
                    click: $.proxy(this.click,this)
                });
            dialog.modal();
        }
    }
    window.CCDialog= function (option) {
        return new CCDialog(option);
    }
})(window.jQuery);