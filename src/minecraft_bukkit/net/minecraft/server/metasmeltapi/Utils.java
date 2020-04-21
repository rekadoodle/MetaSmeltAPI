package net.minecraft.server.metasmeltapi;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import net.minecraft.server.*;

public class Utils {
	
	public static boolean classExists(String className) {
		try {
			Class.forName(className);
		} 
		catch (ClassNotFoundException e) {
			return false; 
		}
		return true;
	}
	
	public static boolean nmsClassExists(String className) {
		return classExists(className) || classExists("net.minecraft.server." + className);
	}
	
	public static int clearBlockID(Block block) {
		return clearBlockID(block.id);
	}
	
	public static int clearBlockID(int id) {
		Block.byId[id] = null;
		return id;
	}
	
	public static void replaceBlock(Block newBlock, Block oldBlock) {
		replaceBlock(newBlock, new EasyField<Block>(oldBlock, Block.class));
	}
	
	public static void replaceBlock(Block newBlock, String ...fields) {
		replaceBlock(newBlock, new EasyField<Block>(Block.class, fields));
	}
	
	public static void replaceBlock(Block newBlock, EasyField<Block> blockField) {
		blockField.removeFinalModifier();
		blockField.set(newBlock);
		Block.byId[newBlock.id] = newBlock;
	}
	
	public static void logError(String... lines) {
		System.out.println(new StringBuilder().append("METASMELTAPI ERROR: ").append(lines[0]).toString());
		for (String message : lines) {
			if(message == lines[0]) continue;
			System.out.println(new StringBuilder().append('\t').append(message).toString());
		}
	}
	
	public static Object getHandler(String path) {
		try { 
			return Utils.class.getClassLoader().loadClass(Utils.class.getPackage().getName() + ".references." + path + ".ConcreteHandler").newInstance(); 
		}
		catch (Throwable e) { e.printStackTrace(); return null; } 
	}

	public static class EasyField<T> {

		private static final EasyField<Integer> modifiersField = new EasyField<Integer>(Field.class, "modifiers");
		public final Field field;
		
		public EasyField(Object value, Class<?> target) {
			this(value, target, null);
		}
		
		public EasyField(Object value, Class<?> target, Object instance) {
			Field correctField = null;
			for (Field field : target.getDeclaredFields()) {
				try {
					if(field.get(instance) == value) {
						field.setAccessible(true);
						correctField = field;
						break;
					}
				} 
				catch (Exception e) { } 
			}
			this.field = correctField;
			if(this.field == null)
			logError("Failed to locate field " + value.getClass().getSimpleName() + " in class " + target.getSimpleName());
		}
		
		public EasyField(Class<?> target, String... names) {
			for (Field field : target.getDeclaredFields()) {
				for (String name : names) {
					if (field.getName() == name) {
						field.setAccessible(true);
						this.field = field;
						return;
					}
				}
			}
			this.field = null;
			logError("Failed to locate field " + names[0] + " in class " + target.getSimpleName());
		}
		
		public boolean exists() {
			return field != null;
		}
		
		@SuppressWarnings("unchecked")
		public T get(Object instance) {
			try {
				return (T) field.get(instance);
			}
			catch (Exception e) { e.printStackTrace(); }
			return null;
		}
		
		public T get() {
			return this.get(null);
		}
		
		public void set(Object instance, T value) {
			try {
				field.set(instance, value);
			} 
			catch (Exception e) { e.printStackTrace(); }
		}
		
		public void set(T value) {
			this.set(null, value);
		}
		
		public void removeFinalModifier() {
			modifiersField.set(field, field.getModifiers() & ~Modifier.FINAL);
		}
		
	}
}
