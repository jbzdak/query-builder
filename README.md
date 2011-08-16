# WHAT IT IS
 

It is a library that allows one to produce text of SQL queries using java API. Since java has beautifull orms
(JPA and so on) I didn't feel the need to produce any other SQL statements than SELECT.

This library contains some usefull distinct features:

1. Ability to pass to query parameters that are compiled into it at query compilation time, so you can have constructs
like:
'''
SELECT foo, bar FROM :table_name WHERE (... next 20 lines of code ;) ..)
'''
be transformed at compile time to:
'''
SELECT foo, bar FROM foo_table WHERE (... next 20 lines of code ;) ..)
'''
2. (will be introduced) Ability to store SQL queries in XML files.
3. Simple query formatting

# STATUS OF THIS SOFTWARE


This software is currently at alpha stage of developement.

# WHEN TO USE IT


1. When you need to produce postgresql queries.
2. When you need to produce queries in different SQL dialect and there are no library that suits your needs.

