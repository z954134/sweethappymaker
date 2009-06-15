package kakeibo.entity
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class Categories
	{
		public  var subcategoryList:ArrayCollection;
		
		public function getItemIndex(subcategory:Subcategory):int
		{
			if (!subcategoryList) return -1;
			
			for (var i:int = 0; i < subcategoryList.length;i++)
			{
				var s:Subcategory = subcategoryList.getItemAt(i) as Subcategory;
				if (s.id == subcategory.id) return i;
			}
			return -1;
		}
	}
}