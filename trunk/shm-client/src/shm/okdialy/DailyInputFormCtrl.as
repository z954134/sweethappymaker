package shm.okdialy {
	import mx.collections.ArrayCollection;
	import mx.core.UIComponent;
	import mx.events.CalendarLayoutChangeEvent;
	import mx.events.FlexEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.ResultEvent;
	
	import shm.common.UICtrlBase;

	public class DailyInputFormCtrl extends UICtrlBase {

		private var view:DailyInputForm;

		private var dateFormatter:DateFormatter = new DateFormatter();

		protected override function doInitialize(component:UIComponent, id:String):void {
			view = DailyInputForm(component);
			dateFormatter.format("YYYY/MM/DD");
		}

		protected override function onCreationCompleted(event:FlexEvent):void {
			view.okDialyDateText.text = dateFormatter.format(new Date());
		}

		public function onDateChooserChanged(event:CalendarLayoutChangeEvent):void {
			var selected:Date = view.dateChooser.selectedDate;
			view.okDialyDateText.text = dateFormatter.format(selected);
			view.selectService.send();
		}
		
		public function onSelectCompleted(event:ResultEvent):void {
			var dialy:Object = event.result.okDialy;
			var items:ArrayCollection = dialy.item as ArrayCollection;
			for (var i:int = 0; i < items.length; i++) {
				view['ok' + (i + 1)].text = items[i];
			}
		}
	}
}