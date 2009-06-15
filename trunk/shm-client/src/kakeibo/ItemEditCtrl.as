package kakeibo
{
	import kakeibo.entity.Category;
	import kakeibo.entity.Item;
	import kakeibo.entity.Subcategory;
	import kakeibo.utils.MyArrayCollection;
	import kakeibo.utils.UICtrlBase;
	import kakeibo.utils.UIUtils;
	
	import mx.collections.ArrayCollection;
	import mx.controls.DataGrid;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.AsyncResponder;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	import mx.validators.Validator;
	
	public class ItemEditCtrl extends UICtrlBase
	{
		private var view:ItemEdit;
	
		private var itemService:RemoteObject = new RemoteObject("itemService");
	
		public var itemList:ItemList;
		
		/** 初期化時のコールバック */ 
		protected override function doInitialize(ui:UIComponent, id:String):void
		{
			view = ItemEdit(ui);
		}
		/** 生成完了時のコールバック */
		protected override function onCreationCompleted(event:FlexEvent):void
		{
			view.categoryField.addEventListener(ListEvent.CHANGE, onCategoryFieldChanged);
			loadCategories();
			loadSubcategories();
		}
		
		public function onItemGridChanged(event:ListEvent):void
		{
			view.item = DataGrid(event.target).selectedItem as Item;
		}
		private function loadCategories():void
		{
			var categoryService:RemoteObject = new RemoteObject("categoryService");
			var token:AsyncToken = categoryService.findAll();
			token.addResponder(new AsyncResponder(
				function (e:ResultEvent, o:Object):void
				{
					view.categories = new MyArrayCollection(e.result as ArrayCollection);
				},
				serviceCallFaultHandler));
		}
		
		private function loadSubcategories():void
		{
			var subcategoryService:RemoteObject = new RemoteObject("subcategoryService");
			var token:AsyncToken = subcategoryService.findAll();
			token.addResponder(new AsyncResponder(
				function (e:ResultEvent, o:Object):void
				{
					view.subcategories = new MyArrayCollection(e.result as ArrayCollection);
				},
				serviceCallFaultHandler));
		} 
		
		/** グリッド変更時 */
		private function changeCategoryAndSubcategory(newSubcategory:Subcategory):void
		{
			var cIdx:int = view.categories.findIndexBy(newSubcategory.category);
			view.categoryField.selectedIndex = cIdx;
			filterSubcategory();
			var sIdx:int = view.subcategories.findIndexBy(newSubcategory);
			view.subcategoryField.selectedIndex = sIdx;
		}

		
		public function onInsertButtonClicked():void
		{
			if (isValid())
			{
				UIUtils.executeIfOk(view, doInsert,
					"新しいレコードを追加してもよろしいですか？");
			}
		}
		
		private function doInsert():void
		{
			var token:AsyncToken = itemService.insert(view.item);
			token.addResponder(new AsyncResponder(
				function (e:ResultEvent, o:Object):void
				{
//					loadItemList();
				},
				serviceCallFaultHandler));
		}
		
		public function onUpdateButtonClicked():void
		{
			if (isValid())
			{
				UIUtils.executeIfOk(view, doUpdate,
					"選択したレコードを更新してもよろしいですか？");
			}
		}
		private function doUpdate():void
		{
			
		}
		

		
		private function isValid():Boolean	{
			var validationResults:Array = Validator.validateAll(view.validators);
			return validationResults.length == 0;
		}
		
		public function onClearButtonClicked():void
		{
			view.item = new Item();	
			view.categoryField.selectedIndex = -1;
			view.subcategoryField.selectedIndex = -1;	
		}
		
		public function onCategoryFieldChanged(event:ListEvent):void	
		{
			filterSubcategory();
			view.subcategoryField.selectedIndex = 0;
		}
		
		
		private function filterSubcategory():void
		{
			var filterFunction:Function = null;
			var selected:Object = view.categoryField.selectedItem;
			if (selected != null)
			{
				var categoryId:int = Category(selected).id;
				filterFunction = function(item:Object):Boolean
				{
					return Subcategory(item).categoryId == categoryId;
				}
			}
			view.subcategories.filterFunction = filterFunction;
			view.subcategories.refresh();
		}
	}
}