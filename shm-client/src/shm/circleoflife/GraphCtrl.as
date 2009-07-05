package shm.circleoflife {
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Image;
	import mx.core.Application;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	
	import shm.common.UICtrlBase;


	public class GraphCtrl extends UICtrlBase {
		private var view:MiniGraph;
		private var elems:ArrayCollection;
		private var graphs:ArrayCollection;

		public function reset(event:Event):void {
			for (var i:int = 0; i < elems.length; i++) {
				var elem:Image = elems.getItemAt(i) as Image;
				elem.source = graph0;
			}
		}

		protected override function doInitialize(component:UIComponent, id:String):void {
			view = MiniGraph(component);
		}

		protected override function onCreationCompleted(event:FlexEvent):void {
			initScores();
			initGraphs();
		}

		private function initScores():void {
			elems = new ArrayCollection();
			for (var i:int = 1; i <= 8; i++) {
				var id:String = 'score' + i;
				var elem:Image = view[id];
				elem.source = graph0;
				elems.addItem(elem);
			}
		}

		private function initGraphs():void {
			graphs = new ArrayCollection();
			for (var i:int = 0; i <= 10; i++) {
				var id:String = 'graph' + i;
				graphs.addItemAt(this[id], i);
			}
		}

		public function draw(elemIdx:int, score:int):void {
			var e:Image = elems.getItemAt(elemIdx - 1) as Image;
			e.source = graphs.getItemAt(score);
		}


		[Embed(source="../assets/graph/chart_0.png")]
		[Bindable]
		private var graph0:Class;

		[Embed(source="../assets/graph/chart_1.png")]
		[Bindable]
		private var graph1:Class;

		[Embed(source="../assets/graph/chart_2.png")]
		[Bindable]
		private var graph2:Class;

		[Embed(source="../assets/graph/chart_3.png")]
		[Bindable]
		private var graph3:Class;

		[Embed(source="../assets/graph/chart_4.png")]
		[Bindable]
		private var graph4:Class;

		[Embed(source="../assets/graph/chart_5.png")]
		[Bindable]
		private var graph5:Class;

		[Embed(source="../assets/graph/chart_6.png")]
		[Bindable]
		private var graph6:Class;

		[Embed(source="../assets/graph/chart_7.png")]
		[Bindable]
		private var graph7:Class;

		[Embed(source="../assets/graph/chart_8.png")]
		[Bindable]
		private var graph8:Class;
		[Embed(source="../assets/graph/chart_9.png")]
		[Bindable]
		private var graph9:Class;

		[Embed(source="../assets/graph/chart_10.png")]
		[Bindable]
		private var graph10:Class;

	}
}