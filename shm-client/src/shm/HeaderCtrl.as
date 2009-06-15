package shm
{
	import flash.events.MouseEvent;
	
	import mx.controls.Alert;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.managers.PopUpManager;
	
	import shm.common.LoginEvent;
	import shm.common.UICtrlBase;

	public class HeaderCtrl extends UICtrlBase
	{

		private var header:Header;

		private var loginWindow:LoginWindow;


		protected override function doInitialize(component:UIComponent, id:String):void
		{
			header = Header(component);
		}


		protected override function onCreationCompleted(event:FlexEvent):void
		{
			header.currentState = "notLoggedIn";
			header.loginoutButton.addEventListener(MouseEvent.CLICK, onLoginOutButttonClicked);

		}

		private function onLoginOutButttonClicked(event:MouseEvent):void
		{
			switch (header.currentState)
			{
				case "loggedIn":
					logout();
					break;
				case "notLoggedIn":
					login();
					break;
				default:
					break;
			}
		}

		private function login():void
		{
			loginWindow = PopUpManager.createPopUp(header, LoginWindow, true) as LoginWindow;
			loginWindow.addEventListener(LoginEvent.LOGIN_COMPLETE, function(event:LoginEvent):void
				{
					header.currentState = "loggedIn";
				});
			PopUpManager.centerPopUp(loginWindow);
		}


		private function logout():void
		{
			header.currentState = "notLoggedIn";
			Alert.show("ログアウトしました。", "Logged out");
		}
	}
}