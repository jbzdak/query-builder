
WHAT IT IS
==========

It is a library that allows one to produce text of SQL queries using java API. Since java has beautifull orms
(JPA and so on) I didn't feel the need to produce any other SQL statements than SELECT.

This library contains some usefull distinct features:

1. Ability to pass to query parameters that are compiled into it at query compilation time, so you can have constructs
like:


WHEN TO USE IT
==============

1. When you need to produce postgresql queries.
2. When you need to produce queries in different SQL dialect and there are no library that suits your needs.