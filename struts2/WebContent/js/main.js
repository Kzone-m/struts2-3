  $(function(){
    var $comment = $('#comment');
    var $label = $('#label');
    var LIMIT = 200;
    $comment.keyup(function(){
      var remain = LIMIT - this.value.length;
      $label.text(remain);
    });
  });