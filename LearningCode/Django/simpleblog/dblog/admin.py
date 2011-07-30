from django.contrib import admin
from simpleblog.dblog.models import Blog, Comment

admin.site.register(Blog)
admin.site.register(Comment)