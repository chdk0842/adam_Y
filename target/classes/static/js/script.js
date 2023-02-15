	TweenMax.staggerFromTo('.blob', 20, {
			cycle : {
				attr : function(i) {
					var r = i * 90;
					return {
						transform : 'rotate(' + r
								+ ') translate( 200 ,0.1) rotate(-' + r + ') '
					}
				}
			}
		}, {
			cycle : {
				attr : function(i) {
					var r = i * 90 + 360;
					return {
						transform : 'rotate(' + r
								+ ') translate( 200 ,0.1) rotate(-' + r + ')'
					}
				}
			},
			ease : Linear.easeNone,
			repeat : -1
		});
		

	