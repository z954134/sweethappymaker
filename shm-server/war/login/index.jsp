<%@page pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<jsp:include page="/header.jsp">
	<jsp:param name="title" value="Index" />
</jsp:include>
<body>

<div class="blue_gradation">

<h2>ログイン
${shm:img("help.ico", "id='help' class='help'") }
</h2>

<form action="${f:url('login')}" method="post">
メンバーID
<input id="memberId" type="text" tooltip="ユーザー名を入力してください。" 
	${f:text("memberId")} class="${f:errorClass('memberId', 'error')}" />
&nbsp;${f:h(errors.memberId)}

<br />
パスワード
<input id="password" type="password" tooltip="パスワードを入力して下さい" 
	${f:text("password")} class="${f:errorClass('password', 'error')}" />
&nbsp;${f:h(errors.password)}<br />
<input id="login" type="submit" value="Login" tooltip="ログインします"/> <br />
</form>

<ul><li>
<a tooltip="Googleアカウントを使ってログインします (Googleのアカウント認証画面に遷移します)" 
	href='${shm:loginUrl("/login/GoogleLogin")}'> 
Googleアカウントでログイン</a></li>
<li>
<a tooltip="アカウントを新規登録をします"
 href="${f:url('/signup')}">アカウント登録</a>
</li></ul>
</div>

<br/>
<script type='text/javascript'>
$(document).ready(function() {
	$('*[tooltip]').each(function() {
    	$(this).qtip({
			content: $(this).attr('tooltip'),
			position: {	corner: { target: 'topRight', tooltip: 'bottomLeft' }}, 
	        style: {
				tip: 'leftBottom',
		        name: 'cream'
			}
    	});
	});


	$('#help').qtip({
		content: {
			title: {
				text: 'ログインについて',
				button: 'Close'
			},
			text: 'へるぷ　へるぷへるぷへるぷへるぷへるぷへるぷへるぷへるぷへるぷ　' 
		},
		position: {
			target: $(document.body), // Position it via the document body...
			corner: 'center' // ...at the center of the viewport
		},
		show: {
			when: 'click', // Show it on click
			solo: true // And hide all other tooltips
		},
		hide: false,
		style: {
			width: { max: 350 },
			padding: '14px',
			border: {
				width: 9,
				radius: 9,
				color: '#666666'
			},
			name: 'light'
		},
		api: {
			beforeShow: function() {
            // Fade in the modal "blanket" using the defined show speed
	            $('#qtip-blanket').fadeIn(this.options.show.effect.length);
	        },
	        beforeHide: function() {
            // Fade out the modal "blanket" using the defined hide speed
	            $('#qtip-blanket').fadeOut(this.options.hide.effect.length);
         	}
		}
	});

	// Create the modal backdrop on document load so all modal tooltips can use it
	$('<div id="qtip-blanket">').css({
         position: 'absolute',
         top: $(document).scrollTop(), // Use document scrollTop so it's on-screen even if the window is scrolled
         left: 0,
         height: $(document).height(), // Span the full document height...
         width: '100%', // ...and full width
         opacity: 0.7, // Make it slightly transparent
         backgroundColor: 'black',
         zIndex: 5000  // Make sure the zIndex is below 6000 to keep it below tooltips!
	}).appendTo(document.body) // Append to the document body
      .hide(); // Hide it initially
});
</script>

</body>
</html>
