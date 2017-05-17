/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.plugins.add( 'dtable',
{
	init : function( editor )
	{
		var table = CKEDITOR.plugins.table,
			lang = editor.lang.table;

		editor.addCommand( 'dtable', new CKEDITOR.dialogCommand( 'dtable' ) );
		editor.addCommand( 'dtableProperties', new CKEDITOR.dialogCommand( 'dtableProperties' ) );

		editor.ui.addButton( 'DTable',
			{
				label : lang.toolbar,
				command : 'dtable'
			});

		CKEDITOR.dialog.add( 'dtable', this.path + 'dialogs/table.js' );
		CKEDITOR.dialog.add( 'dtableProperties', this.path + 'dialogs/table.js' );

		// If the "menu" plugin is loaded, register the menu items.
		if ( editor.addMenuItems )
		{
			editor.addMenuItems(
				{
					dtable :
					{
						label : lang.menu,
						command : 'dtableProperties',
						group : 'dtable',
						order : 5
					},

					dtabledelete :
					{
						label : lang.deleteTable,
						command : 'dtableDelete',
						group : 'dtable',
						order : 1
					}
				} );
		}

		editor.on( 'doubleclick', function( evt )
			{
				var element = evt.data.element;

				if ( element.is( 'table' ) )
					evt.data.dialog = 'dtableProperties';
			});

		// If the "contextmenu" plugin is loaded, register the listeners.
		if ( editor.contextMenu )
		{
			editor.contextMenu.addListener( function( element, selection )
				{
					if ( !element || element.isReadOnly() )
						return null;

					var isTable =element.hasAscendant('table',1)&& !element.getAscendant('table',1).hasAttribute( 'isdetail');

					if (isTable)
					{
						return {
							dtabledelete : CKEDITOR.TRISTATE_OFF,
							dtable : CKEDITOR.TRISTATE_OFF
						};
					}

					return null;
				} );
		}
	}
} );
