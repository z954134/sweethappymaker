package kakeibo.entity
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class Items
	{
		private var itemList_:ArrayCollection;
		
		public function get itemList():ArrayCollection
		{
			return itemList_;
		}
		
		public function set itemList(list:ArrayCollection):void
		{
			itemList_ = new ArrayCollection();
			for (var i:int = 0;i < list.length;i++) 
			{ 
				addItem(list.getItemAt(i));
			}
		}		

		public function addItem(o:Object):void
		{
			var item:Item = Item(o);
			itemList_.addItem(item);
		}
	}
}