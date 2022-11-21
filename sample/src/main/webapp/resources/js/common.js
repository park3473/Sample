/*
$(function() {
	var $tabButtonItem = $('#tab-button li'), $tabSelect = $('#tab-select'), $tabContents = $('.tab-contents'), activeClass = 'is-active';

	$tabButtonItem.first().addClass(activeClass);
	$tabContents.not(':first').hide();

	$tabButtonItem.find('a').on('click', function(e) {
		var target = $(this).attr('href');

		$tabButtonItem.removeClass(activeClass);
		$(this).parent().addClass(activeClass);
		$tabSelect.val(target);
		$tabContents.hide();
		$(target).show();
		e.preventDefault();
	});

	$tabSelect.on('change', function() {
		var target = $(this).val(), targetSelectNum = $(this).prop(
				'selectedIndex');

		$tabButtonItem.removeClass(activeClass);
		$tabButtonItem.eq(targetSelectNum).addClass(activeClass);
		$tabContents.hide();
		$(target).show();
	});
});
*/
// �˾��ҽ�
function popup_content(hideOrshow) {
	if (hideOrshow == 'hide')
		document.getElementById('popup_content_wrap').style.display = "none";
	else
		document.getElementById('popup_content_wrap').removeAttribute('style');
}
window.onload = function() {
	setTimeout(function() {
		popup_content('show');
	}, 100);
}
popup_content();
