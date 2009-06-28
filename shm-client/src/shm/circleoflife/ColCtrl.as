package shm.circleoflife {
	import flash.events.Event;

	import mx.core.IMXMLObject;
	import mx.events.FlexEvent;

	public class ColCtrl implements IMXMLObject {
		private var view:ColPanel;

		private const BASE_URL:String = "http://chart.apis.google.com/chart";
		private const STYLE:String = "cht=r&chs=200x200&chco=FF0000&chxt=x";
		private const LABEL:String = "chxl=0:|elem1|elem2|elem3|elem4|elem5|elem6|elem7|elem8|elem9|elem10";
		private const MARKER:String = "chm=B,FF000080,0,1.0,5.0|h,0000FF,0,0.5,0.5|h,0000FF,0,1.0,1.0";
		private const URL_TEMPLATE:String = BASE_URL + "?" + STYLE + "&" + LABEL +
			"&" + MARKER;


		public function initialized(document:Object, id:String):void {
			view = ColPanel(document);
			view.addEventListener(FlexEvent.CREATION_COMPLETE, creationCompleteHandler);
		}

		public function creationCompleteHandler(event:Event):void {
		}

		public function onSubmitBtnClicked():void {
			var datasetQueryString:String = createDataSetQueryString();
			var chartUrl:String = URL_TEMPLATE + "&" + datasetQueryString;
			view.chart.load(chartUrl);
		}

		private function createDataSetQueryString():String {
			var str:String = "chd=t:" + view.elem1.value + "," + view.elem2.value +
				"," + view.elem3.value + "," + view.elem4.value + "," + view.elem5.value +
				"," + view.elem6.value + "," + view.elem7.value + "," + view.elem8.value +
				"," + view.elem9.value + "," + view.elem10.value + "," + view.elem1.value;
			return str;
		}
	}
}