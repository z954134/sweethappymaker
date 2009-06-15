package kakeibo.utils
{
	import flash.display.Sprite;
	
	import mx.controls.Alert;
	import mx.events.CloseEvent;
	
	public class UIUtils
	{

		public static function executeIfOk(parent:Sprite, func:Function,
						msg:String, title:String="確認"):void
		{
			Alert.show(msg, title, Alert.OK | Alert.CANCEL, parent,
			function (event:CloseEvent):void
			{
				if (event.detail == Alert.OK) {
					func.call();
				}
			}
			);
		}
	}
}