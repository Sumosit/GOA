(function($D){
    var $button    = $D.querySelector('.js-button'),
        $container = $D.querySelector('.js-container');
    $button.addEventListener('mouseup', function(e) {
        var data = e.target.dataset,
            toggleText = $button.innerHTML,
            isVisible = $container.style.display === 'block';


        $container.style.display = isVisible ? 'none' : 'block';
    });
})(document);