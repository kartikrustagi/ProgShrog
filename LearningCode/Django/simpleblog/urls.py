from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    # Example:
    # (r'^simpleblog/', include('simpleblog.foo.urls')),
   
    # Uncomment the admin/doc line below to enable admin documentation:
     (r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
     (r'^admin/', include(admin.site.urls)),
     (r'^simpleblog/$','simpleblog.dblog.views.index'),
     (r'^scripts/(?P<path>.*)$','django.views.static.serve',{'document_root':'/home/kartik/Documents/code/source/django_projects/simpleblog/dblog/scripts'}),
     (r'^simpleblog/blog/(?P<blog_id>\d+)/$','simpleblog.dblog.views.readblog'),
)
