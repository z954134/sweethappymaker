package kakeibo.utils
{
	import mx.controls.ComboBox;

	public class MyComboBox extends ComboBox
	{
		public function MyComboBox()
		{
			super();
		}
		
	    override public function set selectedItem(value:Object):void
    	{

    	    super.selectedItem = value
	    }
	}
}