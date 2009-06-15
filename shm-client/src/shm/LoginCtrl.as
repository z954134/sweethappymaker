package shm
{
	import flash.events.MouseEvent;
	
	import mx.core.UIComponent;
	import mx.events.CloseEvent;
	import mx.events.FlexEvent;
	import mx.managers.PopUpManager;
	
	import shm.common.LoginEvent;
	import shm.common.UICtrlBase;

	public class LoginCtrl extends UICtrlBase
	{
		private var view:LoginWindow;

		public function LoginCtrl()
		{
			super();
		}

		protected override function doInitialize(component:UIComponent, id:String):void
		{
			view = LoginWindow(component);
		}

		protected override function onCreationCompleted(event:FlexEvent):void
		{
			view.cancelButton.addEventListener(MouseEvent.CLICK, onCancelButtonClick);
			view.loginButton.addEventListener(MouseEvent.CLICK, onLoginButtonClicked);
			view.addEventListener(CloseEvent.CLOSE, onCloseButtonClicked);
		}

		private function onLoginButtonClicked(event:MouseEvent):void
		{
			removePopUp();
			view.dispatchEvent(new LoginEvent(LoginEvent.LOGIN_COMPLETE));
		}

		private function onCancelButtonClick(event:MouseEvent):void
		{
			removePopUp();
		}

		private function onCloseButtonClicked(event:CloseEvent):void
		{
			removePopUp();
		}

		private function removePopUp():void
		{
			PopUpManager.removePopUp(view);
		}
	}
}