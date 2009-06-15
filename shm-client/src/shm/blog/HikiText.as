package shm.blog
{
	import flash.text.StyleSheet;
	
	import mx.controls.TextArea;
	import mx.events.FlexEvent;

	public class HikiText extends TextArea
	{
		private var orig:String;

		public function HikiText()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE,
			function(e:FlexEvent):void
			{
				textField.multiline = true;
			});
		}

		public function set cssText(cssText:String):void
		{
			var s:StyleSheet = new StyleSheet();
			s.parseCSS(cssText);
			super.styleSheet = s;
			
		}

		
		public function set originalText(hiki:String):void
		{
			
		}
	}
}