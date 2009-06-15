package kakeibo.entity
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class Categories
	{
		public  var categoryList:ArrayCollection;
		
		public function getItemIndex(category:Category):int
		{
			if (!categoryList) return -1;
			
			for (var i:int = 0; i < categoryList.length;i++)
			{
				var c:Category = categoryList.getItemAt(i) as Category;
				if (c.id == category.id) return i;
			}
			return -1;
		}
	}
}