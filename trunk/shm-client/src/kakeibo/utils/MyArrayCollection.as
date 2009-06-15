package kakeibo.utils
{
	import mx.collections.ArrayCollection;

	public class MyArrayCollection extends ArrayCollection
	{
		private const IDENTIFIER:String = "id";
		public function MyArrayCollection(ac:ArrayCollection=null)
		{
			super(ac ? ac.source : null);
		}
		
		public function findIndexBy(entity:Object):int
		{
			for (var i:int = 0; i < super.length;i++)
			{
				var obj:Object = getItemAt(i);
				if (obj[IDENTIFIER] == entity[IDENTIFIER]) return i;
			}
			return -1;
		}
		
	}
}