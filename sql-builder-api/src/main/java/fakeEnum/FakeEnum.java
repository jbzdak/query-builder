/*
 * Copyright (c) 2011 for Jacek Bzdak
 *
 * This file is part of query builder.
 *
 * Query builder is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Query builder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Query builder.  If not, see <http://www.gnu.org/licenses/>.
 */

package fakeEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Jacek Bzdak
 */
public class FakeEnum<E> {

   private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FakeEnum.class);

   Class<?> clazz;
   Class<?> targetClazz;

   Map<String, ? extends E> constants;
   Map<? extends E, String> constantsReversed;   

   List<? extends E> constantList = new ArrayList<E>();

   List<Field> findConstants(){
      List<Field> fields = new ArrayList<Field>();
      for (Field field : clazz.getFields()){
         final int mod = field.getModifiers();
         if (
            Modifier.isStatic(mod) &&
               Modifier.isFinal(mod) &&
               Modifier.isPublic(mod) &&
               (targetClazz == null || targetClazz.isAssignableFrom(field.getType())) &&
               field.getAnnotation(FakeEnumIgnore.class) == null){
            fields.add(field);
         }
      }
      return fields;
   }

   private Map<String, Object> makeConstansts(List<Field> fields){
      Map<String, Object> constants = new HashMap<String, Object>();
      for (Field f : fields){
         boolean isAccesible = f.isAccessible();
         try {
            f.setAccessible(true);
            constants.put(f.getName(), f.get(null));
         } catch (IllegalAccessException e) {
            LOGGER.warn("Couldnt load field named {} from class", f.getName(), clazz);
         } finally {
            f.setAccessible(isAccesible);
         }
      }
      return constants;
   }

   private Map<String, E> makeConstansts (){
      Map<String, Object> result =
              makeConstansts(findConstants());
      return (Map<String,E>) result;
   }

   public FakeEnum(Class<E> sourceClazz) {
      this(sourceClazz, sourceClazz);
   }

   public FakeEnum(Class<?> sourceClazz, Class<E> targetClazz) {
      this.clazz = sourceClazz;
      this.targetClazz = targetClazz;
      this.constants = makeConstansts();
      Map<E, String> constantsReversed = new HashMap<E, String>();
      for (Map.Entry<String, ? extends E> stringEntry : constants.entrySet()) {
         constantsReversed.put(stringEntry.getValue(), stringEntry.getKey());
      }
      this.constantsReversed = constantsReversed;
//      this.constantList = new ArrayList<E>(constants.values());
   }

   public String nameOf(E value){
      if (constantsReversed.containsKey(value)){
         return constantsReversed.get(value);
      }
      throw new RuntimeException();      
   }



   public Collection<? extends E> values(){
      return constants.values();
   }

   public E valueOf(String s){
      return constants.get(s);
   }


   public String genToString(E o) {
      return o.getClass().getCanonicalName() + "@" + nameOf(o);
   }

   public boolean equals(Object e1, Object e2){
      if (! constantsReversed.containsKey(e1) || ! constantsReversed.containsKey(e2)){
         return false;
      }
      return nameOf((E) e1).equals(nameOf((E) e2));
   }
}
